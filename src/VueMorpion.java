 import java.awt.*;
 import java.awt.Event.*;

 public class VueMorpion extends Panel
 {

	private Button[][] boutons;
	private Label libJoueur;
	private Label libResultat;
	private ModeleMorpion morpion;

	/**
	*Constructeur
	*/
	public VueMorpion()
	{
		super();

		//recupere le morpion
		this.morpion = new ModèleMorpion();

		//On met un border layout dans la fenetre
		this.setLayout(new BorderLayout());

		///Creation du nord
		Panel p1 = new Panel();
		this.add(p1, BorderLayout.NORTH);
		p1.setLayout(new GridLayout(1,2));

		Button effacer = new Button("Effacer");
		effacer.addActionListener(new GestionEffacer());
		p1.add(effacer);
		libJoueur = new Label("Joueur1");
		p1.add(libJoueur);
		///

		///creation du sud
		libResultat = new Label("");
		this.add(libResultat, BorderLayout.SOUTH);
		///


		///Creation du centre
		Panel p2 = new Panel();
		this.add(p2, BorderLayout.CENTER);

		//tous les boutons
		this.boutons = new Buttons[3][3];

		p2.setLayout(new GridLayout(3,3));
		GestionJeux action = new GestionJeux();
		for(int i=0; i<this.boutons.length; i++)
		{
			for(int j=0; j<this.boutons[i].length; j++)
			{
				this.boutons[i][j] = new Button();
				this.bouton[i][j].addActionListener(action);
				p2.add(boutons[i][j]);
			}
		}
		///
	}

	/**
	*Renvoi un couble de coordonnée d'un bouton
	*@param b
	*@return Couple
	*/
	private Couple coordonneesBoutonGrille(Button b)
	{
		int x = 0, y = 0;
		boolean trouve = false;
		for(int i=0; i<this.boutons.length && !trouve; i++)
		{
			for(int j=0; j<this.boutons[i].length && !trouve; j++)
			{
				if(bouton[i][j] == b)
				{
					x = i;
					y = j;
					trouve = true;
				}
			}
		}

		return (new Couple(x,y));
	}

	private char numeroJoueur()
	{
		return (libJoueur.getText().charAt(libJoueur.getText().length() - 1));
	}

	private void effacerBoutonsGrille()
	{
		for(int i=0; i<this.boutons.length; i++)
		{
			for(int j=0; j<this.boutons[i].length; j++)
			{
				//efface le texte de tous les boutons
				bouton[i][j].setText("");
			}
		}
	}



	//Action de l'évènement du bouton effacer
	private class GestionEffacer implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//Traitement de la vue
			VueMorpion.this.effacerBoutonsGrille();
			VueMorpion.this.libJoueur.setText("Joueur1");
			VueMorpion.this.libResultat.setText("");

			//traitement du modèle
			VueMorpion.this.morpion.initialiser();
		}
	}

	//Action de l'évènement du bouton effacer
	private class GestionJeux implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//récupère le joueur
			char joueur = VueMorpion.this.numeroJoueur();
			char joueurOld = joueur;

			//recupère le bouton et ses coordonnées
			Button bouton = (Button)e.getSource();
			int x, y;
			boolean trouve = false;

			//recupere le x du bouton
			x = VueMorpion.this.coordonneesBoutonGrille(bouton).getPremier();
			y = VueMorpion.this.coordonneesBoutonGrille(bouton).getSecond();

			//Si la case n'est pas vide alors on joue
			if(VueMorpion.this.morpion.getTypeCase(x,y) == TypeCarse.VIDE)
			{
				if(joueur == '1')
				{
					bouton.setText("X");
					VueMorpion.this.morpion.setValeurCase(x,y,TypeCase.JOUEUR1);
					VueMorpion.this.libJoueur.setText("Joueur2");
				}
				else
				{
					bouton.setText("O");
					VueMorpion.this.morpion.setValeurCase(x,y,TypeCase.JOUEUR2);
					VueMorpion.this.libJoueur.setText("Joueur1");
				}

				//vérifie s'il y a un joueur qui a gagné
				if(VueMorpion.this.morpion.casesAlignees(x,y))
				{
					VueMorpion.this.libResultat.setText("Le joueur "+joueurOld+" a gagné");
				}

				//Vérifie que l'ont puisse jouer sinon on affiche match nul
				boolean casesVide = false;
				trouve = false;

				for(int i=0; i<this.boutons.length && !trouve; i++)
				{
					for(int j=0; j<this.boutons[i].length && !trouve; j++)
					{
						if(VueMorpion.this.morpion.setValeurCase(i,j) == TypeCase.VIDE)
						{
							casesVide = true;
						}
					}
				}

				if(!casesVide)
				{
					VueMorpion.this.libResultat.setText("Match nul !");
				}

			}


		}
	}

 }