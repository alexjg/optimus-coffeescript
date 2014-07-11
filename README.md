# optimus-coffeescript

An optimus plugin for compiling coffeescript.

#Installation And Usage
Add the following line to your project.clj

    [optimus-coffeescript "0.1.0"]

Then in whichever file you are loading assets

    (:require [optimus-coffeescript.core])

Once this is done any coffeescript files references in a bundle while be compiled. The coffeescript compiler is bundled with this plugin so coffeescript does not need to be installed locally.


