# HeatAndClimate Patch

## Overview

This mod includes my personal patch(es) for Heat And Climate Mod (CurseForge Page), namely its 1.12.2 version.

All modification(s) are made by Mixin, so this mod contains no code of Heat And Climate Mod and its dependency lib.

It is developed mainly for my modpack, but you can use it freely under the license of this project and license of Heat And Climate Mod & Lib.

## Features

- Remove the teleporting of Ivy Badge, the green magic badge, after it prevents your death.
- Prevent the Deepsea Badge, the blue magic badge, from triggered when any GUI is opened. So that when you are typing during chat and pressed "X" (the default blue badge trigger key), you won't be teleported unexpectedly.

## About TemplateDevEnv

This mod utilizes [CleanroomMC/TemplateDevEnv](https://github.com/CleanroomMC/TemplateDevEnv).

This template runs on Java 21, with **Gradle 8.10.1** + **[RetroFuturaGradle](https://github.com/GTNewHorizons/RetroFuturaGradle) 1.4.1** + **Forge 14.23.5.2847**, and fully **coremod and mixin support** that is easy to configure.

### How To Contribute

1. Click `use this template` at the top.
2. Clone the repository that you have created with this template to your local machine.
3. Make sure IDEA is using Java 21 for Gradle before you sync the project. Verify this by going to IDEA's `Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM`.
4. Because HeatAndClimate Lib is not provided on CurseMaven, you may download it manually from CurseForge and put it into `\localDependency`. Currently this repo use `HeatAndClimateLib_1.12.2-3.9.3.jar`. This folder is git-ignored, to prevent distributing HACLib's binary in a license-prohibited way.
5. Open the project folder in IDEA. When prompted, click "Load Gradle Project" as it detects the `build.gradle`, if you weren't prompted, right-click the project's `build.gradle` in IDEA, select `Link Gradle Project`, after completion, hit `Refresh All` in the gradle tab on the right.
6. Run gradle tasks such as `runClient` and `runServer` in the IDEA gradle tab, or use the auto-imported run configurations like `1. Run Client`.
7. Due to this is a mainly Mixin project, it is advisable to use latest [MinecraftDev Fork for RetroFuturaGradle](https://github.com/eigenraven/MinecraftDev/releases).
