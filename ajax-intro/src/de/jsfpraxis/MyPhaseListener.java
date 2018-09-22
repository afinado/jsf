package de.jsfpraxis;

import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *  Einfacher Phase-Listener, um zu verifizieren, dass
 *  Formular komplett gesendet wird. 
 */
public class MyPhaseListener implements PhaseListener {
	
	private static Logger log = Logger.getLogger("de.jsfpraxis.MyPhaseListener"); 

	public PhaseId getPhaseId() {
		// wir sind an allen Phasen interessiert
		return PhaseId.ANY_PHASE;
	}

	public void afterPhase(PhaseEvent pe) {
		log.info("Nach PhaseId : " + pe.getPhaseId());
	}

	public void beforePhase(PhaseEvent pe) {
		log.info("Vor PhaseId : " + pe.getPhaseId());
//		if (pe.getPhaseId().equals(PhaseId.APPLY_REQUEST_VALUES)) {
//			// würde auch in allen anderen Phasen funktionieren
//			Map<String, String> requestParamas = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//			// log.info(Arrays.toString(requestParamas.keySet().toArray()));
//			log.info("Request-Parameter");
//			for (String key : requestParamas.keySet()) {
//				log.info("   " + key + ": " + requestParamas.get(key));
//			}
//			
//		}
		
		if (pe.getPhaseId().equals(PhaseId.APPLY_REQUEST_VALUES)) {
			PartialViewContext pvc = FacesContext.getCurrentInstance().getPartialViewContext();
			log.info("Execute-Ids " + pvc.getExecuteIds());
			log.info("Render-Ids " + pvc.getRenderIds());
		}
	}

}
