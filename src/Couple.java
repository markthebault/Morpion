/**
 * @(#)Couple.java
 *
 *
 * @author
 * @version 1.00 2011/2/11
 */


public class Couple
{
	private int x;
	private int y;

	/**
	 *Construteur qui initialise les positions � 0
	 */
    public Couple()
    {
    	this.x = 0;
    	this.y = 0;
    }

	/**
	 *Construteur qui initialise les positions
	 */
    public Couple(int x, int y)
    {
    	this.x = x;
    	this.y = y;
    }

    /**
     *Renvoi le premier �lement du couple
     *@return x
     */
     public int getSecond()
     {
     	return this.x;
     }

    /**
     *Renvoi le deuxi�me �lement du couple
     *@return y
     */
     public int getPremier()
     {
     	return this.y;
     }
}