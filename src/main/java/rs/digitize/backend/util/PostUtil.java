package rs.digitize.backend.util;

import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;
import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class PostUtil {
	public static final Pattern URL_PATTERN = compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(\\.png|\\.jpe?g|\\.bmp|\\.gif)[-a-zA-Z0-9+&@#/%=~_|]*",
			CASE_INSENSITIVE | MULTILINE);
	public static String getFirstImageUrl(String body) {
		Matcher matcher = URL_PATTERN.matcher(body);

		if (matcher.find())
			return matcher.group(0);

		return null;
	}
}
