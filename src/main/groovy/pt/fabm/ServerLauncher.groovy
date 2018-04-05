package pt.fabm

import org.eclipse.jetty.server.Server

import java.util.concurrent.CountDownLatch

class ServerLauncher {
    private CountDownLatch countDownLatch
    Server server

    static ServerLauncher start(int port) {
        Server server = new Server(port)
        server.handler = new MainHandler()
        server.start()
        ServerLauncher launcher = new ServerLauncher()
        launcher.server = server
        launcher.countDownLatch = new CountDownLatch(1)
        Thread thread = new Thread({
            server.start()
        })
        thread.start()
        launcher.countDownLatch.await()
    }

    void stop() {
        countDownLatch.countDown()
    }

    static void main(String[] args) {
        //default launch
        start(8080)
    }


}

