package com.wicket;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.Strings;

public class CalculatorPage extends OperationsEnum {
	protected BigDecimal buf = BigDecimal.ZERO;
	protected BigDecimal input = BigDecimal.ZERO;
	protected Operations op;
	public CalculatorPage() {
		add(new CalcForm("form"));
	}
	class CalcForm extends StatelessForm {
		public CalcForm(String id) {
			super(id, new CompoundPropertyModel(CalculatorPage.this));
			add(new Label("display", new ComponentPropertyModel("buf")));
			add(new HiddenField("buf"));
			add(new HiddenField("op") {
				@Override
				public IConverter getConverter(Class type) {
					return new IConverter() {
						public Object convertToObject(String value, Locale locale) {
							return Strings.isEmpty(value) ? null : Operations.valueOf(value);
						}
						public String convertToString(Object value, Locale locale) {
							return value.toString();
						}
					};
				}
			});
			add(new TextField("input"));
			
			add(new Button("sevend") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("7");
					input = new BigDecimal("7");
					op = null;
				}
			});
		
			add(new Button("eightd") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("8");
					input = new BigDecimal("8");
					op = null;
				}
			});
			
			add(new Button("nined") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("9");
					input = new BigDecimal("9");
					op = null;
				}
			});
			add(new OperationsButton("div", Operations.div));
			
			add(new Button("fourd") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("4");
					input = new BigDecimal("4");
					op = null;
				}
			});
			add(new Button("fived") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("5");
					input = new BigDecimal("5");
					op = null;
				}
			});
			add(new Button("sixd") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("6");
					input = new BigDecimal("6");
					op = null;
				}
			});
			add(new OperationsButton("mult", Operations.mult));
			
			
			add(new Button("oned") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("1");
					input = new BigDecimal("1");
					op = null;
				}
			});
			add(new Button("twod") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("2");
					input = new BigDecimal("2");
					op = null;
				}
			});
			add(new Button("threed") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("3");
					input = new BigDecimal("3");
					op = null;
				}
			});
			add(new OperationsButton("sub", Operations.sub));
			
			
			add(new Button("zerod") {
				@Override
				public void onSubmit() {
					buf = new BigDecimal("0");
					input = new BigDecimal("0");
					apply();
				}
			});
			add(new Button("clear") {
				@Override
				public void onSubmit() {
					buf = BigDecimal.ZERO;
					input = BigDecimal.ZERO;
					op = null;
				}
			});
			add(new Button("eq") {
				@Override
				public void onSubmit() {
					apply();
				}
			});
			add(new OperationsButton("add", Operations.add));	
		}
	}
	
	void apply() {
		if (op == null)
			buf = input;
		else
			buf = op.eval(buf, input);
	}
	
	class OperationsButton extends Button {
		private Operations myOp;
		public OperationsButton(String id, Operations myOp) {
			super(id);
			this.myOp = myOp;
		}
		@Override
		public void onSubmit() {
			apply();
			op = myOp;
		}
	}
}
