package pt.fabm

import java.util.stream.Collectors

response.setContentType("APPLICATION/JSON")
def body = request.reader.lines().collect(Collectors.joining(System.lineSeparator()))
println 'uri:'+request.requestURI
println 'body:'+body
"""\
{
  "url":"${request.requestURI}",
  "body":"${}"
}

"""

