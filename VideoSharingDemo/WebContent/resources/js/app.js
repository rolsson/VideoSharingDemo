
/**
 * VideoSharing Client application.
 *
 * @author Santiago.PericasGeertsen@oracle.com
 */

var network = function (websocket) {
    return {
        initialize: function() {
            var url = 'ws://' + document.location.host + document.location.pathname + 'videosharing';
            websocket = new WebSocket(url);
            websocket.name = APP.id;
            websocket.onopen = function() {
            };
            websocket.onmessage = function (evt) {
                var command = JSON.parse(evt.data);
                if (command.type == "pause") {
                    APP.pauseVideo();
                } else if (command.type == "play") {
                    APP.playVideo();
                } else if (command.type == "seeked") {
                    APP.seekVideo(command.currentTime);
                } else {
                    alert("Unknown command " + command);
                }
            };
            websocket.onclose = function() {
            };
        },
        send: function(command) {
            websocket.send(command);
        }
    }
};

var APP = {
    id: Math.floor(Math.random() * 10000),

    network: network(null),

    // Cannot use 'this' here after updating window.onload (see below)
    initialize: function () {
        APP.network.initialize();

        var video = APP.getVideo();
        video.addEventListener('play', 
            function (event) { 
                var command = { type: "play" };
                APP.network.send(JSON.stringify(command));
            },
            false);
        video.addEventListener('pause',
            function (event) {
                var command = { type: "pause" };
                APP.network.send(JSON.stringify(command));
            },
            false);
        video.addEventListener('seeked',
            function (event) {
                var command = { type: "seeked",
                                currentTime: APP.getVideo().currentTime };
                APP.network.send(JSON.stringify(command));
            },
            false);
    },

    getVideo: function () {
        return document.getElementsByTagName("video")[0];
    },

    pauseVideo: function () {
        var video = this.getVideo();
        video.pause();
    },

    playVideo: function () {
        var video = this.getVideo();
        video.play();
    },

    seekVideo: function (currentTime) {
        var video = this.getVideo();
        video.currentTime = currentTime;
    }

};

window.onload = APP.initialize;