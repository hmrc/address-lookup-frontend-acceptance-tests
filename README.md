Address Lookup Frontend Acceptance Tests
================================

Browser based acceptance uk.gov.hmrc.integration.tests for address lookup user.

# Running the acceptance tests

Prior to executing the tests ensure you have:

- Docker - If you want to run a browser (Chrome or Firefox) inside a container, or a ZAP container
- Appropriate webdriver binaries installed to run tests against your locally installed Browser(s) - If you don't have these you can
  use [docker containers instead](#running-specs-using-a-containerised-browser---on-a-developer-machine).
- MongoDB installed and running (If you don't have this you can use a docker version)
- Postgresql v10+ installed and running (If you don't have this you can use a docker version)
- Installed/configured [service manager](https://github.com/hmrc/service-manager).

## Start the local services

If you don't have mongodb installed locally you can run it in docker using the following command

    docker run -d --rm --name mongodb -p 27017-27019:27017-27019 mongo:4

If you don't have postgres installed locally you can run it in docker using the following command

    docker run -d --rm --name postgresql -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:10.14

If you are running postgres natively (not in docker) then ensure that the `POSTGRES_USER` and `POSTGRES_PASSWORD` values above, and the `username` and `password` `sm` settings
below, are set appropriately.

To start services locally, run the following:

    sm --start ADDRESS_LOOKUP_SERVICES -r --appendArgs '{
        "ADDRESS_LOOKUP":[
            "-J-Dauditing.consumer.baseUri.port=6001",
            "-J-Dauditing.consumer.baseUri.host=localhost",
            "-J-Dauditing.enabled=true",
            "-J-Dcip-address-lookup-rds.enabled=true", 
            "-J-Dcip-address-lookup-rds.url=jdbc:postgresql://localhost:5432/",
            "-J-Dcip-address-lookup-rds.username=postgres",
            "-J-Dcip-address-lookup-rds.password=postgres"
        ]
    }'

NOTE: The db connection parameters for `address-lookup` service - this is to ensure that it connects to the docker container running locally and to the sidecar container when
running in `jenkins`.

## Running specs

Execute the `run-specs.sh` script:

    ./run-specs.sh

The `run-specs.sh` script defaults to the locally installed `chrome` driver binary. For a complete list of supported param values, see:

- `src/test/resources/application.conf` for **environment**
- [webdriver-factory](https://github.com/hmrc/webdriver-factory#2-instantiating-a-browser-with-default-options) for **browser-driver**

## Running specs using a containerised browser - on a developer machine

The script `./run-local-browser-container.sh` can be used to start a Chrome or Firefox container on a developer machine.

Read more about the script's functionality [here](run-local-browser-container.sh), or invoke `./run-local-browser-container.sh -h`.

To run against a containerised Chrome browser:

```bash
./run-local-browser-container.sh --remote-chrome
./run-specs.sh remote-chrome
```

***Note:** `./run-local-browser-container.sh` should **NOT** be used when running in a CI environment!*

## Running ZAP specs - on a developer machine

You can use the `run-local-zap-container.sh` script to build a local ZAP container that will allow you to run ZAP tests locally.  
This will clone a copy of the dast-config-manager repository in this projects parent directory; it will require `make` to be available on your machine.  
https://github.com/hmrc/dast-config-manager/#running-zap-locally has more information about how the zap container is built.

```bash
./run-local-zap-container.sh --start
./run-local-browser-container.sh --remote-chrome
./run-local-zap-specs.sh
./run-local-zap-container.sh --stop
``` 

***Note:** Results of your ZAP run will not be placed in your target directory until you have run `./run-local-zap-container.sh --stop`*

***Note:** `./run-local-zap-container.sh` should **NOT** be used when running in a CI environment!*

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
