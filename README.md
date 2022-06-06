# Developer Productivity Tool

The purpose of this tool is to reduce toil on certain developer tasks. 

## To compile and run unit tests
```
mvn clean package
```

## Run the cli
```
java -jar target/cli-0.0.1-SNAPSHOT.jar

```

## Features 
To see the full list of commands available , when the cli starts run the help command

```
shell:>help

```

## Backlog
1. Kong specific tasks , create a JWT for a specific consumer
2. Kong OAuth token creation per consumer
3. Kong health status ( without needing kong cli)
4. Check developer account in requires AD Groupse
5. Add cert to local cacerts truststore



