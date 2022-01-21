# Rat Manager
A rat population simulator demonstrating Action Listener, Timer, Swing graphics and objects in Java. This program was created as a project for my AP Computer Science A class. 

## Rat Types

### Skinny Rats
These rats breed at a rate of 15% every 3 seconds. Minimum of 2 skinny rats must be present. 

### Fat Rats
Breeds at the same rate as skinny rats but creates skinny rats instead of fat rats.

###  Zombie Rats
Zombie rats do not breed. Rats must be infested in order to become a zombie rat. These rats eat 6% of skinny rats every 3 seconds. They also infest 3% of fat rats every 3 seconds. If there are no skinny rats remaining, they will eat 1.7% of fat rats every 3 seconds instead of infesting them.

## Simulation Actions

### Breed
Minimum of 2 rats must be present. This forces a breed in addition to the recurring breed every 3 seconds. 

### Feed
Converts 18% of skinny rats into fat rats.

### Spray
Kills 9% of the skinny rats. 

### Zombify
Converts 4% of fat rats into zombie rats. 

### Send Cat
The cat attempts to eat fat rats first, then skinny cats. Takes 21% of the total population if there are enough fat rats left. Otherwise it will take 2 skinny rats instead of 1 fat rat.

### Use Holy
Transforms 11% of zombie rats back into fat rats. 

### Nuke
Kills all rats in the simulation except for 1. 

### Restart
Restarts the simulation back to the beginning of time. 