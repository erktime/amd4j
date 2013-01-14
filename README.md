amd4j
======

A command line tool for running JavaScript scripts that use the [Asychronous Module Defintion API (AMD)](https://github.com/amdjs/amdjs-api/wiki/AMD) for declaring and using JavaScript modules and regular JavaScript script files.

This projects aims to be an alternative to the Rhino version of [r.js](http://requirejs.org/docs/optimization.html) created by [@jrburke](https://github.com/jrburke).

why?
======
Beside all the good work and efforts that [@jrburke](https://github.com/jrburke) did in [r.js](http://requirejs.org/docs/optimization.html) for Java.

I found r.js extremely slow because the use of Rhino. So, **amd4j need to be extremely fast.**

Please note that r.js for Node is exactly the opposite: *extremely fast*

why not?
======
I love AMD!! So, I created this tool (inspired by [r.js](http://requirejs.org/docs/optimization.html)) for processing AMD scripts in Java where ```node.js``` isn't an option

Usage
======

```java
  new Optimizer()
    .optimize(new Config(".", "module.js", "out.js"));
```

what is supported so far?
======

* Processing of single AMD script as input
* Naming modules (the optimizer is able to insert module's names)
* Dependency resolution support
* ```text!``` plugin support
* ```shim``` support

help and support
======
 [Bugs, Issues and Features](https://github.com/jknack/amd4j/issues)

related projects
======
 * [r.js](http://requirejs.org/docs/optimization.html)

credits
======
 * [@jrburke](https://github.com/jrburke)

author
======
 * [@edgarespina](https://twitter.com/edgarespina)

license
======
[Apache License 2](http://www.apache.org/licenses/LICENSE-2.0.html)
