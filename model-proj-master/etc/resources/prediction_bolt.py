from __future__ import absolute_import, print_function, unicode_literals
import storm
import numpy as np
import os
import pickle

from sklearn.externals import joblib

class PredictionBolt(storm.BasicBolt):
    def initialize(self, conf, ctx):
        model_path = 'data/lr.pkl'
        self.clf = joblib.load(model_path)

    def process(self, tup):
        student = tup.values[0]
        gre_score = tup.values[1]
        gpa_score = tup.values[2]
        prestige = tup.values[3]
        retInfo = tup.values[4]

        storm.log('{}:{}:{}'.format(gre_score, gpa_score, prestige))

        row = np.array([gre_score, gpa_score, prestige], dtype=float)
        predArr = self.clf.predict(row)

        pred = str(predArr[0])

        storm.log('{}:{}:{}:{}:{}'.format(student, gre_score, gpa_score, pred, retInfo))

        storm.emit([student, pred, retInfo])

if __name__ == '__main__':
    PredictionBolt().run()
