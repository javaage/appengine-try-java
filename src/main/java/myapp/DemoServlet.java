/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package myapp;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String instanceConnectionName = "newagent-25039:asia-northeast1:la";

		// TODO: fill this in
		// The database from which to list tables.
		String databaseName = "la";

		String username = "postgres";

		String password = "la123456";

		String jdbcUrl = String
				.format("jdbc:postgresql://google/%s?socketFactory=com.google.cloud.sql.postgres.SocketFactory"
						+ "&socketFactoryArg=%s", databaseName, instanceConnectionName);

		Connection connection;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(jdbcUrl, username, password);
//			connection = DriverManager.getConnection("jdbc:postgresql://192.168.120.128:5432/la", "postgres",
//					"19786028");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM LA_USER");
			if(resultSet.next()){
				resp.setContentType("text/plain");
				resp.getWriter().println("{ \"name\": \"" + "has value" + "\" }");
			}else{
				resp.setContentType("text/plain");
				resp.getWriter().println("{ \"name\": \"" + "null value" + "\" }");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setContentType("text/plain");
			resp.getWriter().println("{ \"name\": \"" + e.getMessage() + "\" }");
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("{ \"name\": \"" + jdbcUrl + "\" }");
	}
}
