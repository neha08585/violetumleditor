package com.horstmann.violet.property;

import java.beans.PropertyDescriptor;

import com.horstmann.violet.product.diagram.abstracts.property.MultiLineString;

import eu.webtoolkit.jwt.Signal;
import eu.webtoolkit.jwt.WContainerWidget;
import eu.webtoolkit.jwt.WLabel;
import eu.webtoolkit.jwt.WTextArea;
import eu.webtoolkit.jwt.WVBoxLayout;
import eu.webtoolkit.jwt.WWidget;

public class MultilineStringEditorWidget extends AbstractPropertyEditorWidget<MultiLineString> {

	private WTextArea textAreaComponent;
	private WLabel titleLabel;
	private WContainerWidget editorWidget;
	
	
	public MultilineStringEditorWidget(Object bean, PropertyDescriptor propertyDescriptor) {
		super(bean, propertyDescriptor);
	}

	private WContainerWidget getEditorWidget() {
		if (this.editorWidget == null) {
			this.editorWidget = new WContainerWidget();
			WVBoxLayout editorLayout = new WVBoxLayout();
			editorLayout.addWidget(getTitleLabel());
			editorLayout.addWidget(getTextAreaComponent());
			this.editorWidget.setLayout(editorLayout);
		}
		return this.editorWidget;
	}
	
	private WLabel getTitleLabel() {
		if (this.titleLabel == null) {
			this.titleLabel = new WLabel(getPropertyTitle());
		}
		return this.titleLabel;
	}

	private WTextArea getTextAreaComponent() {
		if (this.textAreaComponent == null) {
			this.textAreaComponent = new WTextArea();
			this.textAreaComponent.changed().addListener(this, new Signal.Listener() {
				public void trigger() {
					MultiLineString currentValue = getValue();
					currentValue.setText(getTextAreaComponent().getText());
					setValue(currentValue);
				}
			});
		}
		return this.textAreaComponent;
	}

	@Override
	protected WWidget getCustomEditor() {
		return getEditorWidget();
	}

	@Override
	protected void updateCustomEditor() {
		getTextAreaComponent().setText(super.getValue().getText());
	}

}
