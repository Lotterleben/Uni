footer: Lotte Steenbrink, iNET HAW Hamburg
slidenumbers: true

# [fit]**Hybrid Routing for the IoT**
##  challenges & opportunities

^bu

---
# **Outline**
- Roadmap
- What is Hybrid Routing?
- Existing concepts
- Experimental work
- Suitability for the Internet of Things
- Conclusion & Outlook

---

# **The big picture**

![inline](./../images/bigpicture.pdf)

---
#       **proactive**         **reactive**
###     

![original](./../images/hybrid_venn.pdf)

^
- AW1: existing; either proactive || reactive
- new technologies & Use Cases @ IoT emerged
- with them: new network characteristic that p/r can't satisfy
- bring p&a together in hybrid
- -> adapt to changes @ network characteristics
°
- comes with own set of challenges:
	* when/where to switch
	* coordinate 2 fundamentally different routing approaches

---
#[fit]**Flashback         **

![original](./../images/gracehopper_1_filter.pdf)

^
- no iot hybrid specifically
- Take ideas from MANET, VANET, DTN
- ZRP etc: 2006 or earlier
- research has stalled since them
- failure or too early? -> too early.

---
# [fit]**The time             **
# [fit]**is now                 **

![original](./../images/the_time_is_now.jpg)


^
- now we have the building blocks in place
- let's take the old ideas and translate them to 2015+
- -> not bore you w/ lists of outdated protocols
- instead -> talk abt concepts that they introduce and use some of them as examples
- -> IoT solutions will have to be derived from that

---
#[fit]**Scope               **

---
<!--#        **route                         area** -->
###         

![inline](./../images/route_centered_example.pdf)![inline](./../images/area_centered_example.pdf)

^
- Lighting system: central control (proactive); connection between sofa lamp & switch if person is in room (reactive)
- warehouse: truckload needs to know abt each other, rest of warehouse less relevant

---
#        **route                         area**

![inline](./../images/route_centered.pdf)![inline](./../images/area_centered.pdf)

^route:
- maintain important routes proac
- unimportant reac
area:
- create routing zones (proac)
- route between zones (DTN[HYMAD] or reactive)

---
#[fit] **Architecture**

---
#       **Protocol             Framework**
![inline](./../images/Walt_Disney_Castle.pdf)![inline](./../images/protocol_example.pdf)


---
# **Protocol**

- fine-grained optimization
	• size
	• computation
	• packet overhead [p2prpl]

^examples:
- P2P-RPL
- ZRP
- WARP
- ZHLS


---
# **Protocol**

- fine-grained optimization
	• size
	• computation
	• packet overhead [p2prpl]
- inflexible
	• protocol updates are hard
	• re-use of existing codebases is hard

^examples:
- P2P-RPL
- ZRP
- WARP
- ZHLS

---

# **Framework**

- flexible
	• mix & match protocols
	• update protocols
	• re-use existing codebases

^examples:
- Node-Centric Hybrid
- SHARP (half)
- HYMAD (half)

---

# **Framework**

- flexible
	• mix & match protocols
	• update protocols
	• re-use existing codebases

- less lightweight
	• no protocol fine-tuning

^examples:
- Node-Centric Hybrid
- SHARP (half)
- HYMAD (half)

---

# **Framework**

- flexible
	• mix & match protocols
	• update protocols
	• re-use existing codebases

- less lightweight
	• no protocol fine-tuning

- semi-frameworks: one fixed, one flexible protocol [SHARP]

^examples:
- Node-Centric Hybrid
- SHARP (half)
- HYMAD (half)

---

#[fit] **Suitability for**
#[fit] **the IoT             **

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

# **Suitability for the IoT**

![inline](./../images/iot_suitability.pdf)

^
-Scope highly dependent on Use Case
- Architecture
	* challenge: make FW lightweight

---
#[fit] **Experimental**
#[fit] **work                **

^
- real-world experience mostly proactive (OLSR, BATMAN) (what abt loadNG?)
- testbed studies rare:
	* few nodes & simple topologies
	(802.15.4, 12 cm // 14 laptops in WARP paper )

---

#[fit] **IoT-Lab             **
![original](./../images/iot-lab.jpg)

^
- we need more realistic evals
- ->PJ goal

---
#[fit] **Conclusion    **
#[fit] **& Outlook      **
^TODO!!!

---
#[fit] **Thank You            **

![original](./../images/thankyou3.jpg)

---

# **protocol list**

![inline] (./../images/protocol_overview.png)


---
<!-- TDODO: update refs! (compare with ausarbeitung) -->
# **References**
<sub>
**[HYMAD]** J. Whitbeck and V. Conan, “HYMAD: hybrid DTN-MANET routing for dense and highly dynamic wireless networks,” CoRR, vol. abs/1006.3426, 2010.
**[IZRP]** P. Samar, M. R. Pearlman, and Z. J. Haas, “Independent zone routing: An adaptive hybrid routing framework for ad hoc wireless networks,” IEEE/ACM Trans. Netw., vol. 12, pp. 595–608, Aug. 2004.
**[NodeCentric]**S. Roy and J. J. Garcia-Luna-Aceves, “Node- Centric Hybrid Routing for Ad Hoc Networks,” in Proceedings of the International Workshop on Mobility and Wireless Access, MobiWac ’02, (Washington, DC, USA), pp. 63–, IEEE Com- puter Society, 2002.
**[p2prpl]** E. Baccelli, M. Philipp, and M. Goyal, “The p2p-rpl routing protocol for ipv6 sensor networks: Testbed experiments,” in Software, Telecommunications and Computer Networks (SoftCOM), 2011 19th International Conference on, pp. 1–6, Sept 2011.
**[SHARP]** V. Ramasubramanian, Z. J. Haas, and E. G. Sirer, “SHARP: A Hybrid Adaptive Routing Protocol for Mobile Ad Hoc Networks,” in Proceedings of the 4th ACM International Symposium on Mobile Ad Hoc Networking & Computing, MobiHoc ’03, pp. 303–314, ACM, 2003.
</sub>

---

# **References**
<sub>
**[TZRP]** L. Wang and S. Olariu, “A Two-Zone Hy- brid Routing Protocol for Mobile Ad Hoc Networks,” IEEE Trans. Parallel Distrib. Syst., vol. 15, pp. 1105–1116, Dec. 2004.
**[WARP]**  P. Sholander, A. Yankopolus, P. Coccoli, and S. Tabrizi, “Experimental comparison of hybrid and proactive MANET routing protocols,” in MILCOM 2002. Proceedings, vol. 1, pp. 513– 518 vol.1, Oct 2002.
**[ZHLS]** M. Joa-Ng and I.-T. Lu, “A peer-to-peer zone-based two-level link state routing for mobile ad hoc networks,” Selected Areas in Communications, IEEE Journal on, vol. 17, pp. 1415–1425, Aug 1999.
**[ZRP]** Z. Haas, M. Pearlman, and P. Samar, “The Zone Routing Protocol (ZRP) for Ad Hoc Networks,” draft, IETF, July 2002.
</sub>

---
# **Image Credit**

##pictograms (all from the Noun Project)
<sub>Computer designed by Ji Sub Jeong, Light bulbs designed by Julien Deveaux, Lamps designed by Renee Ramsey-Passmore, Thomas Le Bas and Becca O'Shea, Rubber Duck designed by Simon Child, Rocking Horse designed by Okan Benn, Circus Elephant designed by Solène Troussé, Lego designed by Okan Benn, Lego designed by jon trillana, Game Boy designed by Simon Child, Castle designed by Road Signs.
</sub>
## **Photos**
<sub>**IoT-Lab** Inria Paris</sub>