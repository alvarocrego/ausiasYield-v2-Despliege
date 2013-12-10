package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract interface Operation {
	public abstract Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
