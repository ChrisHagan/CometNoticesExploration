package com.company.comet

import net.liftweb.http._
import SHtml._
import js._
import net.liftweb.actor._

object NoticeServer extends LiftActor with ListenerManager{
    def createUpdate = "gogogo"
    override def lowPriority={
        case _ => updateListeners()
    }
}
class NoticeEmitter extends CometActor with CometListener{
	def registerWith = NoticeServer
	override def lowPriority = {
        case _ =>{
            notice("This is a notice produced by messaging me at low priority")
            reRender(false)
        }
    }
    def render = ajaxButton("Click this to publish a lift notice",()=>{
    	    notice("This is a notice produced by comet")
            JsCmds.Noop
        })
}
