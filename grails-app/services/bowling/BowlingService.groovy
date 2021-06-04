package bowling

class BowlingService {

    List<List<Integer>> game

    List<Integer> calculateScore(List<List<Integer>> game) {
        this.game = game
        int totalScore = 0
        List scoreList = []

        for (int i = 0; i < game.size(); i++) {
            List<Integer> round = game[i]
            int firstPoint = round[0]
            int secondPoint
            int thirdPoint = 0
            //Final round calculation for Regular 10 round game
            if (i == 9) {
                totalScore += finalRoundRegularGamePoint()
                scoreList.add(totalScore)
                //Stop loop final round have been calculated
                break
            //Strike calculation
            } else if (firstPoint == 10) {
                secondPoint = getNextPointAfterStrikeSpare(1, i)
                thirdPoint = getNextPointAfterStrikeSpare(2, i)
            } else {
                secondPoint = round[1]
                //Spare calculation
                if ((firstPoint + secondPoint) == 10) {
                    thirdPoint = getNextPointAfterStrikeSpare(1, i)
                }
            }
            totalScore += firstPoint + secondPoint + thirdPoint
            scoreList.add(totalScore)
        }
        return scoreList
    }


    int getNextPointAfterStrikeSpare(int increment, int index) {
        int firstPoint
        //Check if there is one more round after current round
        if (index + 1 < game.size()) {
            List nextRound = game[index + 1]
            //Get first shot after current shot
            if (increment == 1) {
                return nextRound[0]
            //Get second shot after current shot
            } else if (increment == 2) {
                firstPoint = nextRound[0]
                //If strike get first point form next round
                if (firstPoint == 10) {
                    return getNextPointAfterStrikeSpare(1, index + 1)
                }
                return nextRound[1]
            }
        }
        //If there is no extra round return 0 points.
        //There are no more rounds to calculate points for.
        return 0
    }

    int finalRoundRegularGamePoint() {
        List round = game[9]
        int firstPoint = round[0]
        int secondPoint = round[1]
        int thirdPoint = 0
        //If strike get final round first and second points
        if (firstPoint == 10) {
            round = game[10]
            secondPoint = round[0]
            thirdPoint = round[1]
        //If spare get current round second point and get final round first point
        } else if ((firstPoint + secondPoint) == 10) {
            secondPoint = round[1]
            round = game[10]
            thirdPoint = round[0]
        }
        return (firstPoint + secondPoint + thirdPoint)
    }
}
