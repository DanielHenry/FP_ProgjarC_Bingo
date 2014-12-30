package player_lib;

import java.io.*;
import java.util.*;
import java.lang.*;

class bingo_player
{
    private int bingo_size;
	private int[][] number_list;
	private int number_of_players;
	private int bingo;
	
	private int[] inserted;
	private int insertedSize;
	
	public bingo_player()
	{
		this.number_list = new int[20][];
		for (int i=0;i<20;i++)
		{
			this.number_list[i] = new int[20];
		}
		this.bingo = 0;
		this.inserted = new int[500];
		this.insertedSize = 0;
	}
	
	public void setBingoSize(int bingo_size)
	{
		this.bingo_size = bingo_size;
	}
	public int getBingoSize()
	{
		return this.bingo_size;
	}
	
	public void setNumberInNumberListAtIndex(int i, int j, int number)
	{
		this.number_list[i][j] = number;
		this.inserted[this.insertedSize] = number;
		this.insertedSize++;
	}
	public int getNumberInNumberListAtIndex(int i, int j)
	{
		return this.number_list[i][j];
	}
	
	public bool isThatNumberInNumberList(int number)
	{
		bool status = false;
		for (int i=0;i<this.insertedSize;i++)
		{
			if (number==inserted[i])
			{
				status = true;
				break;
			}
		}
		return status;
	}
	
	public void setNumberOfPlayers(int number_of_players)
	{
		this.number_of_players = number_of_players;
	}
	public int getNumberOfPlayers()
	{
		return this.number_of_players;
	}
	
	public void coretAngka(int number)
	{
		for (int i=0;i<this.getBingoSize();i++)
		{
			for (int j=0;j<this.getBingoSize();j++)
			{
				if (this.number_list[i][j]==number)
				{
					this.number_list[i][j] = 0;
					return;
				}
			}
		}		
	}
	
	public void checkBingo()
	{
		this.bingo = 0;
		
		//cek horizontal
		for (int i=0;i<this.getBingoSize();i++)
		{
			bool tercoretSatuLine = true;
			for (int j=0;j<this.getBingoSize();j++)
			{
				if (this.number_list[i][j]!=0)
				{
					tercoretSatuLine = false;
					break;
				}
			}
			if (tercoretSatuLine==true) this.bingo++;
		}
		
		//cek vertical
		for (int i=0;i<this.getBingoSize();i++)
		{
			bool tercoretSatuLine = true;
			for (int j=0;j<this.getBingoSize();j++)
			{
				if (this.number_list[j][i]!=0)
				{
					tercoretSatuLine = false;
					break;
				}
			}
			if (tercoretSatuLine==true) this.bingo++;
		}
		
		//cek diagonal (dari pojok kiri atas ke pojok kanan bawah)
		bool tercoretSatuLine = true;
		for (int i=0;i<this.getBingoSize();i++)
		{
			if (this.number_list[i][i]!=0)
			{
				tercoretSatuLine = false;
				break;
			}
		}
		if (tercoretSatuLine==true) this.bingo++;
		
		//cek diagonal (dari pojok kiri bawah ke pojok kanan atas)
		tercoretSatuLine = true;
		for (int i=0;i<this.getBingoSize();i++)
		{
			if (this.number_list[i][i]!=0)
			{
				tercoretSatuLine = false;
				break;
			}
		}
		if (tercoretSatuLine==true) this.bingo++;
	}
	public bool isThisPlayerHasAllBingo()
	{
		if (this.bingo==this.getBingoSize()) return true;
		else return false;
	}
}