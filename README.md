# direcljions

A simple web application to get directions via the Google Maps API. This project
mostly serves as a way to learn how to build webapps using Clojure and [Luminus][1].

[1]: http://www.luminusweb.net/

The `l` is silent.

## Prerequisites

You will need [Leiningen][2] 2.0 or above installed. You will also need a
[Google Maps API key][3] to perform the directions requests.

[2]: https://github.com/technomancy/leiningen
[3]: https://developers.google.com/maps/documentation/directions/start#get-a-key

## Running

First, replace any occurrences of `API_KEY` (there should be two, in: `src/clj/direcljions/routes/home.clj` and `resources/html/directions.html`) with your
actual Google Maps API key. Otherwise, fetching directions and displaying the
map will fail 😱.

Then to start the web server, run:

    lein run

Now you can navigate to `http://localhost:3000` to use the app.

## License

Copyright © 2018 Michel Mansour
