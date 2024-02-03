/*
  Allows for VISUALLY cancelling of animations (swings) from yourself and other players.

  Self animations: Done by constantly resetting the boolean for "are you swinging" to false, thus resetting the animation. 
  Animations of other players: Done by canceling server animation packets of id 0 (swing id), since the client does not need to respond to them and they are only visual.

  This does not affect the 10 tick hit delay, as this module only affects what happens after the hit delay check occurs.

  Tested on anticheat-test.com, tree.ac, eu.loyisa.cn (anticheat servers) and Hypixel on two accounts.
*/

import PogObject from 'PogData'

const settings = new PogObject('Antimations', {
  cancelClientAnimations: false,
  cancelServerAnimations: false
})

const serverAnimationPacket = net.minecraft.network.play.server.S0BPacketAnimation

disableCancelClientAnimations = new Message('&d- Cancel your animations: Currently &aenabled - ', new TextComponent('&c&nDISABLE').setClick('run_command', '/antimations client'))
enableCancelClientAnimations = new Message('&d- Cancel your animations: Currently &cdisabled - ', new TextComponent('&a&nENABLE').setClick('run_command', '/antimations client'))
disableCancelServerAnimations = new Message("&d- Cancel other player's animations: Currently &aenabled - ", new TextComponent('&c&nDISABLE').setClick('run_command', '/antimations server'))
enableCancelServerAnimations = new Message("&d- Cancel other player's animations: Currently &cdisabled - ", new TextComponent('&a&nENABLE').setClick('run_command', '/antimations server'))

register('command', toggle => {
  if (toggle == 'client') {
    settings.cancelClientAnimations = !settings.cancelClientAnimations
    settings.save()
    if (settings.cancelClientAnimations) ChatLib.chat('&5&l< Antimations > &dEnabled cancelling of your animations!')
    else if (!settings.cancelClientAnimations) ChatLib.chat('&5&l< Antimations > &dDisabled cancelling of your animations!')
  }
  else if (toggle == 'server') {
    settings.cancelServerAnimations = !settings.cancelServerAnimations
    settings.save()
    if (settings.cancelServerAnimations) ChatLib.chat("&5&l< Antimations > &dEnabled cancelling of other players' animations!")
    else if (!settings.cancelServerAnimations) ChatLib.chat("&5&l< Antimations > &dDisabled cancelling of other players' animations!")
  }
  else {
    cancelClientAnimations = settings.cancelClientAnimations ? disableCancelClientAnimations : enableCancelClientAnimations
    cancelServerAnimations = settings.cancelServerAnimations ? disableCancelServerAnimations : enableCancelServerAnimations
    ChatLib.chat('&5&l< Antimations 1.0 >')
    ChatLib.chat(cancelClientAnimations)
    ChatLib.chat(cancelServerAnimations)
  }
}).setName('antimations')



let swinger

register('worldLoad', () => { // Set player
  swinger = Player.asPlayerMP().entityLivingBase
})


// Main events

register('tick', () => { // Set "are you swinging" to false constantly, allows for multiple swings to happen
  if (!settings.cancelClientAnimations) return
  if (swinger) swinger.field_82175_bq = false
})

register('packetReceived', (packet, event) => { // Cancel server animation packets, the client doesn't need to respond to these
  if (!settings.cancelServerAnimations) return
  if (!packet.func_148977_d() /* If animation type is 0 (swing)*/) cancel(event)
}).setFilteredClass(serverAnimationPacket)