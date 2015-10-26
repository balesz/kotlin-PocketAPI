package test.extensions

import junit.framework.TestSuite

fun TestSuite.add(vararg suites: TestSuite) {
    for (suite in suites)
        for (i in 0..suite.testCount()-1)
            addTest(suite.testAt(i))
}
