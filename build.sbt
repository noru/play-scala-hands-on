name := "play-scala"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
    jdbc,
    cache,
    ws,
    filters,
    specs2 % Test,
    "mysql"                     %  "mysql-connector-java"   % "5.1.34",
    "net.debasishg"             %% "redisclient"            % "3.0",
    "com.typesafe.play"         %% "play-slick"             % "1.0.1",
    "com.typesafe.play.modules" %% "play-modules-redis"     % "2.4.0",
    "jp.t2v"                    %% "play2-auth"             % "0.14.0",
    "jp.t2v"                    %% "play2-auth-test"        % "0.14.0"
)

resolvers ++= Seq(
    "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
    "google-sedis-fix" at "http://pk11-scratch.googlecode.com/svn/trunk"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
