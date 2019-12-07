from http.server import BaseHTTPRequestHandler, HTTPServer
import json
import cgi
from api import get_info_user, get_event, write_event


class Server(BaseHTTPRequestHandler):

    def get_header(self, header, headers):
        array = str(headers).lower().split("\n")
        for i in array:
            pos = i.find(header.lower())
            if pos != -1:
                return i[len(header) + 2::]

    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.end_headers()

    def do_HEAD(self):
        self._set_headers()

    # GET sends back a Hello world message
    def do_GET(self):
        self._set_headers()
        self.wfile.write(str(json.dumps({'hello': 'world', 'received': 'ok'})).encode("UTF-8"))

    # POST echoes the message adding a JSON field
    def do_POST(self):
        global resp
        ctype, pdict = cgi.parse_header(self.get_header('Content-Type', self.headers))
        # refuse to receive non-json content
        if ctype != 'application/json':
            self.send_response(400)
            self.end_headers()
            return

        # read the message and convert it into a python dictionary
        length = int(self.get_header('Content-Length', self.headers))
        message = json.loads(self.rfile.read(length))
        # use api.py
        if message["method"] == "getInfoUser":
            resp = get_info_user(message['access_token'])
        if message["method"] == "getEvent":
            resp = get_event(message["category"])
        if message["method"] == "writeEvent":
            resp = write_event(message["event"])
        print("Resp From The Server:", resp)
        # send the message back
        self._set_headers()
        self.wfile.write(json.dumps(resp).encode("UTF-8"))


def run(server_class=HTTPServer, handler_class=Server, port=8008):
    server_address = ('', port)
    httpd = server_class(server_address, handler_class)

    print('Starting httpd on port %d...' % port)
    httpd.serve_forever()


if __name__ == "__main__":
    from sys import argv

    if len(argv) == 2:
        run(port=int(argv[1]))
    else:
        run()
