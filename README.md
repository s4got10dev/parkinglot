# Parking Lot
Console application to emulate parking lot

## How to use
Project already has gradle wrapper (so you don't need to have it installed). 
All other dependencies will be downloaded by it.

  
### Execution
Execute gradle build (It may take a while in the first run):
```sh
$ ./gradlew build
```
After the message of BUILD SUCCESS, the application is ready to run:
```sh
$ java -jar build/libs/parking-1.0-SNAPSHOT.jar
```

### Usage example

#### Console interactive
```sh
$ java -jar build/libs/parking-1.0-SNAPSHOT.jar
Welcome to Parking Lot Application!
Enter your command, help or exit:
$ create_parking_lot 3
Created a parking lot with 3 slots
$park KA-01-HH-1234 White
Allocated slot number: 1
$park KA-01-HH-9999 White
Allocated slot number: 2
$park KA-01-BB-0001 Black
Allocated slot number: 3
$leave 2
Slot number 2 is free
$status
Slot No.     Registration No.          Colour         
1            KA-01-HH-1234             White          
3            KA-01-BB-0001             Black  
$registration_numbers_for_cars_with_colour White.
KA-01-HH-1234
```
#### Console parse file
```sh
$ java -jar build/libs/parking-1.0-SNAPSHOT.jar
```
```sh
$file src/test/resources/file_inputs.txt
Processing commands from file: src/test/resources/file_inputs.txt
Created a parking lot with 6 slots
Allocated slot number: 1
Allocated slot number: 2
Allocated slot number: 3
Allocated slot number: 4
Allocated slot number: 5
Allocated slot number: 6
Slot number 4 is free
Slot No.     Registration No.          Colour         
1            KA-01-HH-1234             White          
2            KA-01-HH-9999             White          
3            KA-01-BB-0001             Black          
5            KA-01-HH-2701             Blue           
6            KA-01-HH-3141             Black          

Allocated slot number: 4
Sorry, parking lot is full
KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333
1, 2, 4
6
Not found
```

## Tech stack
- Backend
    - Kotlin
- Testing
    - Groovy
    - Spock
- Gradle



