# Setting up the tests and node dev server

1. Install [Java 11 Development Kit](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html)
2. Install [node](https://nodejs.org/en/download/) by following the instructions. Or you could also use [chocolatey](https://chocolatey.org/) on Windows or [Homebrew](https://brew.sh/) on Mac/Linux
3. Inside the `frontend/` directory run `npm install`
4. Start the FE dev server with `npm run dev`. The Spring app proxies requests to `/dist/**` to the dev server on your local machine
5. Make sure [webdriver](https://www.selenium.dev/documentation/en/webdriver/driver_requirements/) is in your path
6. Install a lombok plugin for your editor of choice 
7. Run the tests via the gradle test task to verify the app is set up

# Running the backend app 

From the root directory of the app, execute `.\gradlew.bat bootRun` on Windows or `./gradlew bootRun` on Mac/Linux

# Code Coverage

We are tracking code coverage using JaCoCo. A report is generated after every test run and placed in the `build` directory of the code.

# Recommendation Engine

Inside the `libs` directory, you will find a jar for the eRis recommendation engine. Their client success team gave us 
the go ahead to keep the jar in our codebase until John, our DBA, has time to set up an artifactory instance. This engine 
is used to place recommendations on each Product page based on customer trends. However, right now the recs don't look very 
good because we have yet to start providing them a feed of customer purchase history. That being said, our sales have 
seen a noticeable uptick since we shipped this feature.