# Dec 12 Hangout minutes

## Attendees
- John
- Charlie
- Lotte

## Status

1) John - Vicky (?) will start her analysis on v05 of the draft any day now

2) Discussion about RFC5444

- Charlie - Almost finished with list description
- John & Lotte - as described on the Mailing List? (Thread:  OrigNdx and TargNdx )
- Charlie - yes
- Lotte - I stll don't see why we need lists to hold two elemnts instead of just referring to them as what they are (originating and taregt node address)
- Charlie - well, RFC5444 calls for address lists and it is unambiguous either way
- Lotte - yes, but the list representation seems to be harder to read and describe to me. Also, RFC5444 may use Address Lists internally, but I did not have to give Hennings API lists, I told the API I needed (for example) the TargNode Address to be in the message, with (for example) Metric and SeqNum TLVs associated with it, and then the same for the OrigNode Address and that's it.
- Charlie - The AODVv2 (or any other) Draft should not be written to please a particular API (for example hennings oonf api)
- Lotte - very much agree, I didn't use the right words, sorry

IIRC, then a discussion ensued in which Charlie said he is in favor of being in control of the packet structure as much as possible, while Lotte was in favor of letting the RFC5444 parser/builder of the implemnetor's choice take care of the details and just specifying „I have an address x with TLVs y and z associated with it“. This would mean treating *RFC5444* (not particular implementations!) as an API. Of course, anyobody implementing aodvv2 who wants as much ontrol over the internal packet structure as possible can implement their own RFC5444 builder/parser which suits their needs best.
But I'm unable to put that into dialogue form.. please feel free to add or correct things if I misrepresented anything.

- Lotte - Illustrations in Appendix are still unclear on the structure of the packets (i.e. which TLV belongs to which address, are thre any message TLVs etc), but contain a lot of info that should be handled by the RFC5444 parser/builder, not the routing protocol 
- Charlie - Lotte, write text for a packet format description and we'll discuss that
- Lotte - ok, will do
- agreement that the explanatory document promised by Thomas is much needed
- Lotte - talked to Henning & Thomas about it, apparently Thomas has the document and promised to publish it soon; will inqure about it again
	
4) Charlie - issues need to be closed (the resolved ones) or resolved

- proposes that every action concerning the issue tracker should be discussed briefly on the mailing list first (i.e. “I think issue #123 was resolved by x, any objections or can I close it?”)
- agreement that the above should be done
- issues should be resolved by end of january(?)

5) Charlie - would like to have a new version out by christmas


## Tasks

*everyone:* Go trough issues and close/resolve them (after brief discussion on the ML)
*Charlie:*

- work on issues

*Lotte:* 

- ask Thomas about RFC5444 best practices draft
- write proposal for a packet format description

## Next meeting 
Dec 19th, 2014