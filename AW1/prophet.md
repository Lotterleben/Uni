# PRoPHET

## features
- delay tolerant


## strategy
- distribute messages to more than 1 nieghbor (but choose wisely)
- monitor ntwork activity
- predict node movement patterns, either physical (i.e. people moving) or "virtual" (static node, but switching off/on to save battery, lossy connection...)
- when 2 nodes have contact (human moved, device switched on), they exchange delivery predictability info.
- when predictability is good (determined by forwarding strategy), nodes ask neighbor to transmit data for them

## sources
- implementation by the DTN2 project: [link](http://www.dtnrg.org/docs/code/DTN2/doc/manual/ro_prophet.html)