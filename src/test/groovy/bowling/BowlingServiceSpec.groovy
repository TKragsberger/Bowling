package bowling

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class BowlingServiceSpec extends Specification implements ServiceUnitTest<BowlingService> {

    List<List<Integer>> testList = [[]]

    @Unroll("testing game with #list.size() the final score should be #lastScore")
    void "testing calculate score"() {
        given:
            testList = list
        when:
            List<Integer> result = service.calculateScore(testList)
        then:
            result.last() == lastScore

        where:
            list                                                                                                 | lastScore
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 10]] | 300
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [7, 3], [10, 0]]   | 277
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [7, 1], [0, 0]]    | 263
            [[10, 0], [10, 0], [10, 0]]                                                                          | 60
            [[10, 0], [9, 1], [8, 2]]                                                                            | 48
            [[9, 1], [8, 1], [10, 0]]                                                                            | 37
            [[7, 2], [8, 1], [1, 4]]                                                                             | 23
    }

    @Unroll("getNextPointAfterStrikeSpare with increment: #increment and index: #index gives point: #point and with testList: #testList")
    def "testing getNextPointAfterStrikeSpare"() {
        given:
            service.game = testList
        when:
            def getNextPointAfterStrikeSpare = service.getNextPointAfterStrikeSpare(increment, index)
        then:
            getNextPointAfterStrikeSpare == point
        where:
            testList                   | increment | index | point
            [[10, 0]]                  | 1         | 0     | 0
            [[10, 0]]                  | 2         | 0     | 0
            [[9, 1]]                   | 1         | 0     | 0
            [[10, 0], [10, 0], [9, 0]] | 1         | 0     | 10
            [[10, 0], [10, 0], [9, 0]] | 2         | 0     | 9
            [[10, 0], [9, 1], [10, 0]] | 1         | 1     | 10
            [[9, 1], [9, 1], [10, 0]]  | 1         | 0     | 9
    }

    @Unroll
    def "testing finalRoundRegularGamePoint"() {
        given:
            service.game = testList
        when:
            def finalRoundRegularGamePoint = service.finalRoundRegularGamePoint()
        then:
            finalRoundRegularGamePoint == point
        where:
            testList                                                                                             | point
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 10]] | 30
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [7, 3], [10, 0]]   | 20
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [7, 1], [0, 0]]    | 8

    }
}

