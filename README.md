# Antimations

Forge mod port: https://github.com/Yedelo/AntimationsMod

ChatTriggers site: https://www.chattriggers.com/modules/v/Antimations



Allows for VISUALLY cancelling of animations (swings) from yourself and other players.



Self animations: Done by constantly resetting the boolean for "are you swinging" to false, thus resetting the animation.

Animations of other players: Done by canceling server animation packets of id 0 (swing id), since the client does not need to respond to them and they are only visual.



This does not affect the 10 tick hit delay, as this module only affects what happens after the hit delay check occurs.

Tested on anticheat-test.com, tree.ac, eu.loyisa.cn (anticheat servers) and Hypixel on two accounts.

Use /antimations for the main config, and /antimations (client/server) to toggle them respectively.

Example video: https://youtu.be/behmZVH4Qw0
