package rs.digitize.backend.bean.filter;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import rs.digitize.backend.search.GenericSpecificationConverter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class CountFilter<T> implements Filter {
	public static final String HEADER_NAME = "X-Data-Count";
	private final GenericSpecificationConverter converter = new GenericSpecificationConverter();
	private final Class<? extends JpaRepository<T, ?>> repositoryClass;
	protected JpaRepository<T, ?> repository;

	protected CountFilter(Class<? extends JpaRepository<T, ?>> clazz) {
		this.repositoryClass = clazz;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (!request.getMethod().equals(HttpMethod.GET.name())) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		initPostRepository(servletRequest);
		String query = getQuery(servletRequest);
		Specification<T> specification = (Specification<T>) converter.convert(query);
		response.setHeader(HEADER_NAME, getCount().toString());
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public abstract Long getCount();

	private String getQuery(ServletRequest servletRequest){
		String query = servletRequest.getParameter("q");
		if (query == null)
			return "";
		return query;
	}

	private void initPostRepository(ServletRequest request) {
		if (repository == null) {
			ServletContext servletContext = request.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			repository = webApplicationContext.getBean(repositoryClass);
		}
	}
}
