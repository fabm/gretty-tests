package pt.fabm

response.setContentType("APPLICATION/JSON")
println 'entrou '+request.requestURI
"""\
{"url":"${request.requestURI}"}"""

