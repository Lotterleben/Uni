footer: Lotte Steenbrink, iNET HAW Hamburg	
slidenumbers: true

<!-- TODO: QUELLEN!!!-->

# [fit]**Hybrid Routing for the IoT**
##  challenges & opportunities

^ 
- AW1: existing; either proactive || reactive 
- new technologies & Use Cases @ IoT have emerged
- with them: new network characteristic

---
#       **proactive**         **reactive**
###     

![original](./../images/hybrid_venn.pdf)

^ 
- short intro to proactive & reactive (1 sentence)
- bring p&a together in hybrid prots this sem 
- -> adapt to changes @ network characteristics
- comes with own set of challenges:
	* when/where to switch
	* coordinate 2 fundamentally different routing approaches
	* most research stems from time where IoT vision didnt exist -> necessary to adjust & advance

---
# **Flashback** 

![original](./../images/gracehopper_1_filter.pdf)

^
TODO: insert pictures of 2000 vs 2014 hardware/visions for mobile nodes here
...aaactually, the use cases haven't chaned that much... => differenciate betwen MANET and IoT?
- ZRP etc: 2006 or earlier
- research has stalled since them
- failure or too early? -> too early.

---
# **The time is now** 

^research is 10 years old, BUT!! 
- now we have the building blocks in place
- let's take the old ideas and translate them to 2014+

---
# **Some fancy headline which introduces that I'm going to talk about concepts now** 
TODO: maybe I can just skip his & say what I want to say in the slide before this one

^
- I'm not going to bore you with lists of outdated protocols
- instead, i'm going to talk about concepts that they introduce and use some of them as examples
- comprehensive list can be found at TODO

---
#        **route                         area** 

![inline](./../images/route_centered.pdf)![inline](./../images/area_centered.pdf)

^
TODO: speaker notes that explain both approaches & give protocol examples


---
#        **route                         area** 

![inline](./../images/route_centered_example.pdf)![inline](./../images/area_centered_example.pdf)

^
- Lighting system: central control (proactive); connection between sofa lamp & switch if person is in room (reactive)
- warehouse: truckload needsa to know abt each other, rest of warehouse less relevant

TODO: attribution into bibliography: Computer designed by Ji Sub Jeong from the Noun Project, Light bulbs designed by Julien Deveaux from the Noun Project, 
Lamps designed by Renee Ramsey-Passmore, Thomas Le Bas and Becca O'Shea from the Noun Project || Rubber Duck designed by Simon Child from the Noun Project, Rocking Horse designed by Okan Benn from the Noun Project, Circus Elephant designed by Solène Troussé from the Noun Project, Lego designed by Okan Benn from the Noun Project, Lego designed by jon trillana from the Noun Project, Game Boy designed by Simon Child from the Noun Project

---
# **Protocol vs Framework** 
TODO: gegenüberstellende Visualisierung (zB LEGOs?)

^TODO: speaker notes

---
# **Protocol** 

- fine-grained optimization [p2prpl]
	• size
	• computation
	• packet overhead
- inflexible
	• protocol updates are hard
	• re-use of existing codebases is hard

^TODO: speaker notes

---
# **Framework** 
<!-- TODO: content & examples -->

- flexible
	• mix & match protocols
	• update protocols
	• re-use existing codebases

- less lightweight
	• no protocol fine-tuning

- semi-frameworks: one fixed, one flexible protocol [SHARP]
^TODO: speaker notes

---
# **But what about the IoT?** 
TODO: okay cool, and what's suitable for the IoT now? how do we translate this to the 21st century?

<!--
- look at:
- network topology
- traffic patterns

pro framework:
- IoT has lots of use cases-> framework can be customized to fit them
- easier to keep pace with rapidly evolving technology

pro protocol:
- easy to install, less decisions
- can be optimized for size, overhead, less abttery drain
-->

---
# **Conclusion** 



---
# **Thank You** 

![](./../images/skating_cows.jpg)

^TODO: attribution in references: Skating Cows by Mark Turnauckas

---

# **References** 
<sub>
**[p2prpl]** E. Baccelli, M. Philipp, and M. Goyal, “The p2p-rpl routing protocol for ipv6 sensor net- works: Testbed experiments,” in Software, Telecommunications and Computer Networks (SoftCOM), 2011 19th International Conference on, pp. 1–6, Sept 2011.
**[SHARP]** V. Ramasubramanian, Z. J. Haas, and E. G. Sirer, “SHARP: A Hybrid Adaptive Routing Protocol for Mobile Ad Hoc Networks,” in Proceedings of the 4th ACM International Symposium on Mobile Ad Hoc Networking & Computing, MobiHoc ’03, pp. 303–314, ACM, 2003.
</sub>

---
# **Image Credits**

##pictograms
Computer designed by Ji Sub Jeong from the Noun Project, Light bulbs designed by Julien Deveaux from the Noun Project, 
Lamps designed by Renee Ramsey-Passmore, Thomas Le Bas and Becca O'Shea from the Noun Project || Rubber Duck designed by Simon Child from the Noun Project, Rocking Horse designed by Okan Benn from the Noun Project, Circus Elephant designed by Solène Troussé from the Noun Project, Lego designed by Okan Benn from the Noun Project, Lego designed by jon trillana from the Noun Project, Game Boy designed by Simon Child from the Noun Project