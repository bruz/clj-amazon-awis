# clj-amazon-awis

[![Build Status](https://secure.travis-ci.org/bruz/clj-amazon-awis.png)](http://travis-ci.org/bruz/clj-amazon-awis)

A Clojure library for the Amazon Alexa Web Information Services (AWIS) API.
It's a very light wrapper around the API that returns the response XML as a
parsed struct-map.

## Usage

Up to date Leiningen and Maven configurations are on the
[Clojars page](https://clojars.org/clj-amazon-awis).

### Some examples

```clojure
(require '[clj-amazon-awis.core :as awis])

(def auth {:aws-access-key "YOUR ACCESS KEY" :aws-secret-key "YOUR SECRET KEY"})

(awis/url-info {:response-group "UsageStats" :url "http://clojure.org"} auth)

(awis/traffic-history {:response-group "History" :url "http://clojure.org"} auth)

(awis/category-browse {:response-group "Categories" :path "Top/Arts"} auth)

(awis/category-listings {:response-group "Listings" :path "Top/Arts"} auth)

(awis/sites-linking-in {:response-group "SitesLinkingIn" :url "http://clojure.org"} auth)
```

The [Amazon AWIS documentation](http://docs.amazonwebservices.com/AlexaWebInfoService/latest/)
is the best source for available options.

Also see the [Marginalia docs](http://bruz.github.com/clj-amazon-awis/).

## Development

Tests use [Midje](https://github.com/marick/Midje), so to run the test install
the Leiningen plugin for midge and run `lein midje`.

## License

Copyright (C) 2012 Bruz Marzolf

Distributed under the Eclipse Public License, the same as Clojure.
