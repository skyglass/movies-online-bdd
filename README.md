# Getting the app running

1. Install [Java 11 Development Kit](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html)
2. Install [node](https://nodejs.org/en/download/) by following the instructions. Or you could also use [chocolatey](https://chocolatey.org/) on Windows or [Homebrew](https://brew.sh/) on Mac/Linux
3. Inside the `frontend/` directory run `npm install`
4. Start the FE dev server with `npm run dev`. The Spring app proxies requests to `/dist/**` to the dev server on your local machine
5. Make sure [webdriver](https://www.selenium.dev/documentation/en/webdriver/driver_requirements/) is in your path
6. Run the tests via the gradle test task to verify the app is set up

# Running the app 

From the root directory of the app, execute `.\gradlew.bat bootRun` on Windows or `./gradew bootRun` on Mac/Linux

# Code Coverage

We are tracking code coverage using JaCoCo. A report is generated after every test run and placed in the `build` directory of the code. 