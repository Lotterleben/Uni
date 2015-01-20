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
# **Outline**
- What is Hybrid Routing?
- Existing Concepts
	• area- vs route-centered
	• protocol vs framework
- Transition to the IoT
- Conclusion & Outlook

---
# **The big picture**

![inline](./../images/bigpicture.pdf)

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

![original](./../images/the_time_is_now.jpg)


^research is 10 years old, BUT!! 
- now we have the building blocks in place
- let's take the old ideas and translate them to 2014+
- -> I'm not going to bore you with lists of outdated protocols
- instead, i'm going to talk about concepts that they introduce and use some of them as examples
- -> IoT solutions will have to be derived from that
- comprehensive list can be found at TODO (appendix?)

---
#[fit]**Scope               ** 

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
- warehouse: truckload needs to know abt each other, rest of warehouse less relevant

---
#[fit] **Architecture** 

---
#       **Protocol             Framework** 
![inline](./../images/Walt_Disney_Castle.pdf)![inline](./../images/protocol_example.pdf)

^TODO: lieber disney castle? :D

---
# **Protocol** 

- fine-grained optimization [p2prpl]
	• size
	• computation
	• packet overhead

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
#[fit] **Experimental**
#[fit] **work                **

---
#[fit] **Suitability for**
#[fit] **the IoT             ** 

---

# **Suitability for the IoT** 

![inline](./../images/iot_suitability.pdf)

^
-Scope highly dependent on Use Case
- Architecture
	• challenge: make FW lightweight


<!--
TODO: okay cool, and what's suitable for the IoT now? how do we translate this to the 21st century?


Issues:
- which proactive/reactive protocols translate to the IoT?
- tradeoff flexibility of framework (good bc so may use cases) vs optimizability of protocol


- look at:
- network topology
- traffic patterns

pro framework:
- IoT has lots of use cases-> framework can be customized to fit them
- easier to keep pace with rapidly evolving technology
con:
- may be heavyweight


pro protocol:
- easy to install, less decisions
- can be optimized for size, overhead, less abttery drain
con:
- slow with adaptions of new things
- inflexible (many use cases)

Framework pros probably win, but fw has to be designed to be lightweight!

-->

---
#[fit] **Conclusion    **
#[fit] **& Outlook      ** 

---
#[fit] **Thank You            ** 
 
![original](./../images/thankyou3.jpg)

^TODO: attribution in references: Skating Cows by Mark Turnauckas

---

# **References** 
<sub>
**[IZRP]** P. Samar, M. R. Pearlman, and Z. J. Haas, “Independent zone routing: An adaptive hybrid routing framework for ad hoc wireless networks,” IEEE/ACM Trans. Netw., vol. 12, pp. 595–608, Aug. 2004.
**[NodeCentric]**S. Roy and J. J. Garcia-Luna-Aceves, “Node- Centric Hybrid Routing for Ad Hoc Networks,” in Proceedings of the International Workshop on Mobility and Wireless Access, MobiWac ’02, (Washington, DC, USA), pp. 63–, IEEE Com- puter Society, 2002.
**[p2prpl]** E. Baccelli, M. Philipp, and M. Goyal, “The p2p-rpl routing protocol for ipv6 sensor networks: Testbed experiments,” in Software, Telecommunications and Computer Networks (SoftCOM), 2011 19th International Conference on, pp. 1–6, Sept 2011.
**[SHARP]** V. Ramasubramanian, Z. J. Haas, and E. G. Sirer, “SHARP: A Hybrid Adaptive Routing Protocol for Mobile Ad Hoc Networks,” in Proceedings of the 4th ACM International Symposium on Mobile Ad Hoc Networking & Computing, MobiHoc ’03, pp. 303–314, ACM, 2003.
**[TZRP]** L. Wang and S. Olariu, “A Two-Zone Hy- brid Routing Protocol for Mobile Ad Hoc Networks,” IEEE Trans. Parallel Distrib. Syst., vol. 15, pp. 1105–1116, Dec. 2004.
</sub>

---

# **References** 
<sub>
**[WARP]**  P. Sholander, A. Yankopolus, P. Coccoli, and S. Tabrizi, “Experimental comparison of hybrid and proactive MANET routing protocols,” in MILCOM 2002. Proceedings, vol. 1, pp. 513– 518 vol.1, Oct 2002.
**[ZHLS]** M. Joa-Ng and I.-T. Lu, “A peer-to-peer zone-based two-level link state routing for mobile ad hoc networks,” Selected Areas in Communications, IEEE Journal on, vol. 17, pp. 1415–1425, Aug 1999.
**[ZRP]** Z. Haas, M. Pearlman, and P. Samar, “The Zone Routing Protocol (ZRP) for Ad Hoc Networks,” draft, IETF, July 2002.
</sub>

---
# **Image Credit**

##pictograms (all from the Noun Project)
<sub>Computer designed by Ji Sub Jeong, Light bulbs designed by Julien Deveaux, Lamps designed by Renee Ramsey-Passmore, Thomas Le Bas and Becca O'Shea, Rubber Duck designed by Simon Child, Rocking Horse designed by Okan Benn, Circus Elephant designed by Solène Troussé, Lego designed by Okan Benn, Lego designed by jon trillana, Game Boy designed by Simon Child, Castle designed by Road Signs.
</sub>