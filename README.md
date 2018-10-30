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

Create a `dev-config.edn` configuration file in the top-level of the project. It should look something like this:

```
{:dev true
 :port 3000
 ;; when :nrepl-port is set the application starts the nREPL server on load
 :nrepl-port 7000

 }
```
(Luminus creates this file when you run `lein new luminus my-app` but omits it from source control.)

Next, replace any occurrences of `API_KEY` (there should be two, in: `src/clj/direcljions/routes/home.clj` and `resources/html/directions.html`) with your
actual Google Maps API key. Otherwise, fetching directions and displaying the
map will fail 😱.

Then to start the web server, run:

    lein run

Now you can navigate to `http://localhost:3000` (or whatever port you specified in the config file) to use the app.

## License

Copyright © 2018 Michel Mansour
