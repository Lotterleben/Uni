# 3 min Vortrag Vorbereitung

## das Internet of Things
Autonome, drahtlose Kommunikation zwischen intelligenten Gegenständen (*smart objects*), beispielsweise zur Home Automation, Rettung im Gefahrenfall...
IdR kommen batteriebetriebene Sensorknoten mit wenig Rechenkraft zum Einsatz.

### Eigenschaften

- IoTs sind meshnetzwerke
- *viele* knoten
- eher wenig traffic
- traffic kann regelmäßig & "bursty" sein
- low power & lossy network --> oft schlechte Verbindung (*-> übergang zum nächsten punkt*)

### Herausforderungen für Routingprotokolle

- wenig speicherplatz
- kommunikation ist teuer (bzgl batterie)
- muss mit (häufigen) Ausfällen (mobilität, batterie alle, sleep mode...)
- "richtiger Weg" nicht nur link qulity, sondern auch "node quality"

### was will ich machen?
Es gibt bisher 1 "offizielles" IoT Routingprotokoll (RPL), was aber nicht in allen Anwendungsfällen ideal ist. Geplant ist daher:  
Klassifizierung & Überblick über verschiedene Lösungsansätze, auch außerhalb der ROLL Aktivitäten. Welche Forschung/ Deployments gibt es, wie erfolgreich und vergleichbar sind die? Was lässt sich perspektivisch zu dem Thema noch machen?

### rpl
- für einen use case + erweiterungen
- -> ist rpl + erweiterung besser als einfach gleich ein frisches protokoll für den use case?

## Relevantes

### Special Interest Groups
- SIGCOMM

### journals
- [IEEE/ACM Transactions on Networking (ToN) ](http://dl.acm.org/citation.cfm?id=J771&CFID=396754175&CFTOKEN=67051998)
- [IEEE Internet of Things journal](http://iot-journal.weebly.com/index.html)

### conferences
- annual [SIGCOMM](http://sigcomm.org/events/sigcomm-conference) conference
- [Sensys](http://www.sigcomm.org/events/sensys-conference) (for sensed networking)
- [IEEE World Forum on the IoT](http://sites.ieee.org/wf-iot/about/)
- [International Conference Series on
Intelligent Sensors, Sensor Networks and Information Processing (ISSNIP)](http://www.issnip.org/)
- [IEEE International Conference on Wireless and Mobile Computing, Networking and Communications  (WiMob)](http://conferences.computer.org/wimob2014/)

- [ICSCN](http://www.ieee.org/conferences_events/conferences/conferencedetails/index.html?Conf_ID=32586)

- mal reingucken: [events in cooperation with SIGCOMM](http://www.sigcomm.org/events/in-cooperation-with-acm-sigcomm)



#### Interessant, aber nicht 100% passend
- [Workshop on Hot Topics in Networks (HotNets) ](http://www.sigcomm.org/events/hotnets-workshop)
- [Architectures for Networking and Communication Systems](http://www.sigcomm.org/events/ancs-conference)
- [Conference on emerging Networking EXperiments and Technologies (CoNEXT)](http://www.sigcomm.org/events/conext-conference)

### IETF stuff
- ROLL (Routing over Low Power & Lossy Networks ) Working Group
- MANET Working Group
- DTN (Delay Tolerant Networks) WG
- ITS (intelligen transport systems) BoF


### wichtige Forscher_innen


### Sonstiges
- [iot.ieee.org](http://iot.ieee.org/) für nen Überblick über neue Entwicklungen

# Recherche
## schlagworte
- wireless sensor networks
- 

## weiterverfolgbare Ansätze
- Routen mit mehr als 1 Pfad (macht das schon iwer? wird dabei auch node-status/kapazität berücksichtigt?)
- 




