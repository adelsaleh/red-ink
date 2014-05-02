/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redink;

/**
 *
 * @author bayan
 */
public interface IWord extends Comparable<IWord>{
    public String getWord();
	public int getOffset();
    public ETag getTag();
    public boolean equals(IWord word);
}
