##Main purpose
This code was presented during SpreadIT 2015 conference, as a code sketches for the talk: Genealogy of DDD.
It does not implement whole problems, it does not contain any tests etc. again these are only sketches. 

##Business cases to which sceches are referring too#
*Warehouse Racks' Tracker* keeps track of items on a shelf racks.

 - Warehouse Racks Tracker, should be able to tell, on which shelf, of which rack an item is stored.
 - Warehouse Racks Tracker, should be able to allocate new or already used racks shelf, under which an item is stored.
 - Whenever an item is the last item that can be stored in a location, when new item of same type is about to be stored, 
 a new location should be assigned for it. Every item has it's own limit of how much of it can be stored in a single location.
 - Whenever an item is the last item that is removed from the location, the location should be freed to start storing 
 other items.