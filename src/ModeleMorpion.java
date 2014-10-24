/**
 * @(#)ModeleMorpion.java
 *
 *
 * @author
 * @version 1.00 2011/2/11
 */


public class ModeleMorpion
{
	private TypeCase[][] plateau;

	/**
	* Constructeur de la classe
	*/
	public ModeleMorpion()
	{
		//creation du plateau
		this.plateau = new TypeCase[3][3];

		//initialisation
		this.initialiser();
	}
	/**
	* initialiser le pl	ateau a vide
	*/
	public void initialiser()
	{
		for(int i = 0; i<this.plateau.length; i++)
		{
			for(int j = 0; j<this.plateau[i].length; j++)
			{
				this.plateau[i][j] = TypeCase.VIDE;
			}
		}
	}


	/**
	* Informe de l'alignement des cases passées en paramètre
	*@param x,
	*@param y
	*@return boolean
	*@exception PositionInvalide
	*/
	public boolean casesAlignees(int x, int y) throws PositionInvalide
	{
		boolean allignee = false;
		//declanche une exception quand la position est invalide
		if(x <= this.plateau.length || x < 0 || y <= this.plateau[x].length || y < 0)
		{
			throw new PositionInvalide();
		}


		for(int i = 0; i<this.plateau.length; i++)
		{
			if(this.plateau[i][0] == this.plateau[i][1] && this.plateau[i][0] == this.plateau[i][2])
			{
				allignee = true;
			}

			if(this.plateau[0][i] == this.plateau[1][i] && this.plateau[0][i] == this.plateau[2][i])
			{
				allignee = true;
			}

			if(this.plateau[i][i] == this.plateau[i][i] && this.plateau[i][i] == this.plateau[i][i])
			{
				allignee = true;
			}

			int l = this.plateau.length-1;
			if(this.plateau[l-i][l-i] == this.plateau[l-i][l-i] && this.plateau[l-i][l-i] == this.plateau[l-i][l-i])
			{
				allignee = true;
			}
		}
	}

	/**
	*Revoie la valeur d'une case
	*@param x,
	*@param y
	*@return TypeCase
	*@exception PositionInvalide
	*/
	public TypeCase getValeurCase(int x, int y) throws PositionInvalide
	{
		//declanche une exception quand la position est invalide
		if(x <= this.plateau.length || x < 0 || y <= this.plateau[x].length || y < 0)
		{
			throw new PositionInvalide();
		}

		//vérifie que la position soit valide
		return this.plateau[x][y];
	}

	/**
	*Redéclaration de toString
	*/
	public String toString()
	{
		//affiche le plateau de jeux
		String affichage;

		for(int i = 0; i<this.plateau.length; i++)
		{
			for(int j = 0; j<this.plateau[i].length; j++)
			{
				switch(this.plateau[i][j])
				{
					case TypeCase.VIDE:
						affichage += ' ';
					break;
					case TypeCase.JOUEUR1:
						affichage += 'X';
					break;
					case TypeCase.JOUEUR2:
						affichage += 'O';
					break;
				}

				affichage += '|';
			}
			affichage += '\n';
		}
	}

	/**
	*Affecte une valeur a la case
	*@param x
	*@param y
	*@param type
	*@exception PositionInvalide
	*/
	public void setValeurCase(int x, int y, TypeCase type) throws PositionInvalide, CaseOccupee
	{
		//declanche une exception quand la position est invalide
		if(x <= this.plateau.length || x < 0 || y <= this.plateau[x].length || y < 0)
		{
			throw new PositionInvalide();
		}

		if(this.plateau[x][y] != TypeCase.VIDE)
		{
			throw new CaseOccupee();
		}

		this.plateau[x][y] = type;
	}



}