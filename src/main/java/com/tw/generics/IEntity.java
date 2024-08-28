package com.tw.generics;

import java.io.Serializable;

public interface IEntity <Id extends Serializable> extends Serializable {

	public Id getId();

	public boolean isNew();
}
