package bowling

import io.micronaut.http.HttpResponse

import static io.micronaut.http.HttpStatus.BAD_REQUEST
import static io.micronaut.http.HttpStatus.OK

class BowlingController extends UtilHelper {

    BowlingService bowlingService
    SkatService skatService

    def index() {
        HttpResponse<String> response = skatService.getPointsFromServer()
        List<List<Integer>> body = parseBodyToJSON(response.body.get())

        List<Integer> gameScore = bowlingService.calculateScore(body)
        String json = createJSONbody(gameScore)

        HttpResponse httpResponse = skatService.postPointsToServer(json)
        if (httpResponse.status == OK) {
            return render(httpResponse.body())
        }
        return render(BAD_REQUEST)
    }
}
