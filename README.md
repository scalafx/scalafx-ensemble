ScalaFX Ensemble
================

[![Build Status](https://travis-ci.org/scalafx/scalafx-ensemble.svg?branch=master)](https://travis-ci.org/scalafx/scalafx-ensemble)  

ScalaFX Ensemble provides a gallery of over 60 sample applications illustrating how
[ScalaFX] can be used for creation of user interfaces controls, charts, graphics, media and web views.

![ScalaFX Ensemble Application - Demo navigation](http://scalafx.github.io/scalafx-ensemble/images/ScalaFX_Ensemble-grid-50p.png)

![ScalaFX Ensemble Application - Demo tab](http://scalafx.github.io/scalafx-ensemble/images/ScalaFX_Ensemble-demo-50p.png)
![ScalaFX Ensemble Application - Demo source](http://scalafx.github.io/scalafx-ensemble/images/ScalaFX_Ensemble-source-50p.png)

Each example can be executed within the ScalaFX Ensemble application, its source code can be easily seen there too.
Example can be saved, from within ScalaFX Ensemble application, as a stand-alone [SBT] project. [IntelliJ IDEA] can load SBT projects if you have IntelliJ's Scala plugin installed. SBT projects can be converted to [Eclipse] projects using [sbteclipse] plugin.

ScalaFX Ensemble can be used by ScalaFX beginners as a set of examples and by more experienced users as a visual
reference. Larger size screenshots and information about changes can be found on the
[ScalaFX Ensemble Home Page].

This project is inspired by [JavaFX Ensemble].

How to run ScalaFX Ensemble
---------------------------

ScalaFX Ensemble can be easily run from the source code, instructions are provided below.
In the future, we will also provide an excitable version.

### Requirements ###

To compile and run the project you only need to have:

*  [Java JDK] 11 or newer
*  [SBT] 1 or newer
*  ScalaFX Ensemble source code. All missing dependencies, including a proper version of Scala and ScalaFX, will be downloaded by SBT.

Alternatively you can download a stand-alone installer from the [Releases], see [Stand-Alone Application](#stand-alone-application) below.

The older version of ScalaFX Ensemble for ScalaFX 8 are on branch [SFX-8](https://github.com/scalafx/scalafx-ensemble/tree/SFX-8), ScalaFX 2.2 (Java 1.7) is on branch
[SFX-2](https://github.com/scalafx/scalafx-ensemble/tree/SFX-2).

### Compile and Run using SBT ###

Once you have [Java JDK] and [SBT] installed, you can compile and run ScalaFX Ensemble from command prompt using `sbt`:

1. Open command prompt
2. Change directory to where you saved the ScalaFX Ensemble source code (directory containing this README file).
3. Type `sbt run`

When run the very first time, SBT will download all needed dependencies including Scala and ScalaFX.

### Saving and Building Individual Examples

ScalaFX Ensemble Application gives you ability to save each example as a separate SBT project. You can then build and run that example.

To save an example as a stand-alone SBT project:  

1. Start ScalaFX Ensemble Application
2. Select an example from the panel on the left. For instance "Charts" > "Adv Candle Stick Chart"
3. Select on "Source" tab above the example
4. Select "Save SBT Project..." then select **empty** directory where to save the project

To run the saved example, assuming that you have [SBT] and JDK installed:

1. From a command line prompt navigate to the directory you saved the example
2. At command line type: `sbt run`. This will download necessary dependencies, build, and run the example.
 

### Compile and Run using Intellij IDEA ###

[IntelliJ IDEA] with Scala plugin can import project settings from an SBT configuration.
Simply select `File > Import Project...` and point to location of the ScalaFX Ensemble `build.sbt`.

Ensemble requires some resources to be generated from the sources to run.
The best way to run it is by defining an SBT task in Run Configuration:
1. Select "Run" > "Edit Configurations"
2. Clock on "+" and then on "SBT task"
3. Give it a name "Ensemble"
3. In "Task" field type "run" and click OK

Stand-Alone Application
-----------------------

Installer for a stand-alone version of ScalaFX Ensemble Application are available for Windows, macOS, and Linux from the [Releases] page.

![Install4J](https://www.ej-technologies.com/images/product_banners/install4j_medium.png)

Installers are created using [Install4J] and the [sbt-install4j] plugin.

Building Installers
-------------------
To build an installer you need to have [Install4J] installed. 

Use SBT task `install4j -m _os_`, for instance to create Windows installer:

```
sbt> install4j -m windows
```

Note that due to native dependencies in JavaFX distribution you have to build installer on a destination OS. Current SBT setup does not allow for cross-building on different OS. It is technically possible but not currently implemented in this project.

For more information about SBT `install4j` task see [sbt-install4j].

Mailing list
------------

To post questions or send feedback about ScalaFX Ensemble or ScalaFX in general, please use ScalaFX discussion groups:

* [scalafx-user] - for users of ScalaFX

* [scalafx-dev] - for ScalaFX contributors/committers

* [ScalaFX on StackOverflow](https://stackoverflow.com/questions/tagged/scalafx) 


License
-------

Copyright (c) 2012-2020, ScalaFX Project
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
* Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.
* Neither the name of the ScalaFX Project nor the
names of its contributors may be used to endorse or promote products
derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE SCALAFX PROJECT OR ITS CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

[Apache2]: https://www.apache.org/licenses/LICENSE-2.0.html
[Eclipse]: http://www.eclipse.org/
[Install4J]: https://www.ej-technologies.com/products/install4j/overview.html
[IntelliJ IDEA]: http://www.jetbrains.com/idea/
[Java JDK]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[JavaFX Ensemble]: http://www.oracle.com/technetwork/java/javafx/samples/index.html
[sbt-install4j]: https://github.com/jpsacha/sbt-install4j
[sbteclipse]: https://github.com/sbt/sbteclipse
[SBT]: http://www.scala-sbt.org/

[ScalaFX]: http://scalafx.org
[scalafx-dev]: https://groups.google.com/forum/?fromgroups#!forum/scalafx-dev
[scalafx-user]: https://groups.google.com/forum/?fromgroups#!forum/scalafx-users
[ScalaFX Ensemble Home Page]: http://scalafx.github.com/scalafx-ensemble/
[Releases]: https://github.com/scalafx/scalafx-ensemble/releases
