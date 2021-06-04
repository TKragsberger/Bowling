package bowling

import groovy.json.JsonSlurper

class UtilHelper {

    def jsonSlurper = new JsonSlurper()
    String token

    List<List<Integer>> parseBodyToJSON(body) {
        def jsonBody = jsonSlurper.parseText(body.toString())
        if (!jsonBody) {
            return null
        }
        token = jsonBody.token
        jsonBody.points
    }

    String createJSONbody(List<Integer> list) {
        return '{"token": "' + token + '", "points": ' + list + '}'
    }
}
