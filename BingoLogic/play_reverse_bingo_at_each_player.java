import java.io.*;
import java.util.*;
import java.lang.*;
import player_lib.bingo_player;

class play_reverse_bingo_at_each_player
{
	public static void main(String[] argv)
	{
		bingo_player saya = new bingo_player();
		
		int bingo_size;
		//pemain mendapatkan ukuran bingo yang akan dimainkan. Didapatkan dari server. Asumsi ukuran bingo dimasukkan ke dalam variabel bingo_size
		saya.setBingoSize(bingo_size);
		
		int number_of_players;
		//pemain mendapatkan banyaknya orang yang bermain dalam satu room dari server. Asumsi banyaknya orang yang bermain dimasukkan ke dalam variabel number_of_players  
		saya.setNumberOfPlayers(number_of_players);
		
		bool belum_menentukan_number_list = true;
		
		while (true)
		{
			bool apakah_saya_boleh_jalan_sekarang;
			//pemain diberitahu oleh server apakah ia boleh berjalan sekarang atau tidak. Asumsi disimpan di variabel apakah_saya_boleh_jalan_sekarang
			if (apakah_saya_boleh_jalan_sekarang==true)
			{			
				if (belum_menentukan_number_list==true)
				{
					for (int i=0;i<saya.getBingoSize();i++)
					{
						for (int j=0;j<saya.getBingoSize();j++)
						{
							int number;
							while (true)
							{
								//tentukan angka pada number_list index i dan j. Asumsi angka ditampung pada variabel number.
								
								if (saya.isThatNumberInNumberList(number)) continue;  //cek apakah angka tersebut sudah dipilih?? jika ya, minta ulang input angka
								else break;											  //jika tidak, keluar dari perulangan
							}
							saya.setNumberInNumberListAtIndex(i,j,number);
						}
					}
					belum_menentukan_number_list = false;
				}
				else
				{
					
					int angka_yang_diumumkan;
					while (true)
					{
						//pemain mengisi angka yang ingin dicoret. Asumsi dimasukkan pada variabel angka_yang_diumumkan.
						//if angka sudah dicoret, continue
						//else break
					}
					//angka_yang_diumumkan dikirim ke server
					
				}
			}
			int angka_yang_harus_dicoret;
			//pemain menerima angka dari server. Asumsi angka dimasukkan ke dalam variabel angka_yang_harus_dicoret
			saya.coretAngka(angka_yang_harus_dicoret);
			saya.checkBingo();
			if (saya.isThisPlayerHasAllBingo())
			{
				//kirim status ke server bahwa pemain ini harus disingkirkan dari permainan
			}
			//menerima status dari server apakah pemain ini masih boleh main??
			if (/*masih boleh main*/) //lanjutkan permainan
			else //bisa milih, mau keluar dari room, apa masih mau stay
			
			//menerima status dari server apakah sudah ada yang menang atau tidak
			if (/*sudah ada yang menang*/) //umumkan pemenang, break, kembali ke lobby
			else //lanjutkan permainan
		}
	}
}