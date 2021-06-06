package kodlama.io.hrms.core.adapters;

import java.rmi.RemoteException;
import java.util.Locale;

import org.springframework.stereotype.Service;

import kodlama.io.hrms.entities.concretes.Candidates;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements MernisCheckService{

	@Override
	public boolean checkIfRealPerson(Candidates candidate) {
		
		KPSPublicSoapProxy client = new KPSPublicSoapProxy();
		
		boolean result = false;

			try {
				result=client.TCKimlikNoDogrula(Long.parseLong(candidate.getNationalityId()), candidate.getFirstName().toUpperCase(new Locale("tr")), 
					candidate.getLastName().toUpperCase(new Locale("tr")), candidate.getBirthDate());
			} catch (RemoteException e) {

				e.printStackTrace();
			}
	
		
		return result;
	}
	
	
}
