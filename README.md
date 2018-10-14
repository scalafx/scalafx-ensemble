ScalaFX Ensemble
================

ScalaFX Ensemble provides a gallery of over 60 sample applications illustrating how
[ScalaFX](http://scalafx.org) can be used for creation of user interfaces controls, charts, graphics, media and web views.

![ScalaFX Ensemble Application - Demo navigation](http://scalafx.github.io/scalafx-ensemble/images/ScalaFX_Ensemble-grid-50p.png)

![ScalaFX Ensemble Application - Demo tab](http://scalafx.github.io/scalafx-ensemble/images/ScalaFX_Ensemble-demo-50p.png)
![ScalaFX Ensemble Application - Demo source](http://scalafx.github.io/scalafx-ensemble/images/ScalaFX_Ensemble-source-50p.png)

Each example can be executed within the ScalaFX Ensemble application, its source code can be easily seen there too.
Example can be saved, from within ScalaFX Ensemble application, as an stand-alone [SBT](http://www.scala-sbt.org/) project,
with option to convert to [Eclipse](http://www.eclipse.org/) or [IntelliJ IDEA](http://www.jetbrains.com/idea/) project.

ScalaFX Ensemble can be used by ScalaFX beginners as a set of examples and by more experienced users as a visual
reference. Larger size screenshots and information about changes can be found on the
[ScalaFX Ensemble Home Page](http://scalafx.github.com/scalafx-ensemble/).

This project is inspired by [JavaFX Ensemble](http://www.oracle.com/technetwork/java/javafx/samples/index.html).

Larger size screenshots and information about changes can be found on the
[ScalaFX Ensemble Home Page](http://scalafx.github.com/scalafx-ensemble/)


How to run ScalaFX Ensemble
---------------------------

ScalaFX Ensemble can be easily run from the source code, instructions are provided below.
In the future, we will also provide an excitable version.

### Requirements ###

To compile and run the project you only need to have:

*  [Java 11 JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
*  [SBT](http://www.scala-sbt.org/) (v.1 or newer) installed.
*  ScalaFX Ensemble source code

All missing dependencies, including proper version of Scala and ScalaFX, will be downloaded by SBT.

The older version of ScalaFX Ensemble for ScalaFX 8 on branch [SFX-8](https://github.com/scalafx/scalafx-ensemble/tree/SFX-8), ScalaFX 2.2 (Java 1.7) is on branch
[SFX-2](https://github.com/scalafx/scalafx-ensemble/tree/SFX-2).

### Compile and Run using SBT ###

Once you have [JDK 11](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
and [SBT](http://www.scala-sbt.org/) installed, you can compile and run ScalaFX Ensemble from command prompt using `sbt`:

1. Open command prompt
2. Change directory to where you saved the ScalaFX Ensemble source code (directory containing this README file).
3. Type `sbt run`

When run the very first time, SBT will download all needed dependencies including Scala and ScalaFX.

### Compile and Run using Intellij IDEA ###

[IntelliJ IDEA](http://www.jetbrains.com/idea/) with Scala plugin can import project settings from an SBT configuration.
Simply select `File > Import Project...` and point to location of the ScalaFX Ensemble `build.sbt`.

Ensemble requires some resources to be generated from the sources to run.
To best way to run it is be defining an SBT task in Run Configuration:
1. Select "Run" > "Edit Configurations"
2. Clock on "+" and then on "SBT task"
3. Give it a name "Ensemble"
3. In "Task" field type "run" and click OK


Project Status
--------------

This project is in a "stable" pre-release state, the main missing future planned for the first release is support
for building native executables.


Creating Native Installer
-------------------------
To create a native installer for current OS (Windows, Linux or MacOSX) issue following command:

```
sbt jdkPackager:packageBin
```

Mailing list
------------

To post questions or send feedback about ScalaFX Ensemble or ScalaFX in general, please use ScalaFX discussion groups:

* [scalafx-user](https://groups.google.com/forum/?fromgroups#!forum/scalafx-users) - for users of ScalaFX

* [scalafx-dev](https://groups.google.com/forum/?fromgroups#!forum/scalafx-dev) - for ScalaFX contributors/committers

* [ScalaFX][https://stackoverflow.com/questions/tagged/scalafx] on StackOverflow


License
-------

Copyright (c) 2012-2018, ScalaFX Project
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

