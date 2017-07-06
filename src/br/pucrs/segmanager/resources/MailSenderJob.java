package br.pucrs.segmanager.resources;

import java.util.Calendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.pucrs.segmanager.dao.SeguroDAO;
import br.pucrs.segmanager.model.Seguro;

public class MailSenderJob implements Job {

	private SeguroDAO seguroDAO;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		seguroDAO = new SeguroDAO();

		Seguro filter = new Seguro();

		List<Seguro> seguros = seguroDAO.findSegurosSemNotificacao();
		
		for (Seguro seg : seguros) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 11);

			if (seg.getDtFimVigencia().before(cal.getTime())) {
				MailBuilder mb = new MailBuilder();
				mb.addAssunto("Vencimento do seguro " + seg.getId()).addFrom("ssegmanager@gmail.com")
					.addMensagem("Prezado(a) " + seg.getSegurado().getNome() + ", \n"
							+ "Informamos que o seu seguro vence nos próximos 10 dias. \n"
							+ "Entre em contato com seu corretor para maiores informações. \n")
					.addTo(seg.getSegurado().getEmail()).enviarEmail();
				seg.setStNotificado("S");
				seguroDAO.save(seg);
			}
		}
	}

}
