/*
 * MusicPane.java
 *
 * Created on February 9, 2004, 2:01 PM
 */

package plugin.charactersheet.gui;

import pcgen.core.PlayerCharacter;
import pcgen.io.exporttoken.VarToken;

import javax.swing.JCheckBox;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author ddjone3
 */
public class MusicPane extends javax.swing.JPanel {
	private PlayerCharacter pc;

	private ArrayList checkList = new ArrayList();

	private Properties pcProperties;

	private boolean updateProperties = false;

	/** Creates new form RagePanel */
	public MusicPane() {
		initComponents();
		setLocalColor();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {// GEN-BEGIN:initComponents
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		checkPanel = new javax.swing.JPanel();
		musicText = new javax.swing.JTextArea();

		setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				1, 0));

		jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabel1.setText("BARDIC MUSIC");
		jPanel1.add(jLabel1);

		add(jPanel1);

		jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3,
				javax.swing.BoxLayout.X_AXIS));

		jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				1, 0));

		jLabel2.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel2.setText("Uses Per Day");
		jPanel2.add(jLabel2);

		jPanel3.add(jPanel2);

		checkPanel.setLayout(new java.awt.FlowLayout(
				java.awt.FlowLayout.CENTER, 0, 1));

		jPanel3.add(checkPanel);

		add(jPanel3);

		musicText.setFont(new java.awt.Font("Dialog", 0, 10));
		musicText.setLineWrap(true);
		musicText.setWrapStyleWord(true);
		add(musicText);

	}// GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel checkPanel;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JPanel jPanel2;

	private javax.swing.JPanel jPanel3;

	private javax.swing.JTextArea musicText;

	// End of variables declaration//GEN-END:variables

	/**
	 * Set color
	 */
	public void setColor() {
		setLocalColor();
		refresh();
	}

	/**
	 * Set local color
	 */
	public void setLocalColor() {
		jPanel1.setBackground(CharacterPanel.header);
		jPanel3.setBackground(CharacterPanel.border);
		jPanel2.setBackground(CharacterPanel.header);
		jPanel2.setBorder(new javax.swing.border.LineBorder(
				CharacterPanel.border));
		jPanel1.setBorder(new javax.swing.border.LineBorder(
				CharacterPanel.border));
		checkPanel.setBackground(CharacterPanel.white);
		checkPanel.setBorder(new javax.swing.border.LineBorder(
				CharacterPanel.border));
		musicText.setBorder(new javax.swing.border.LineBorder(
				CharacterPanel.border));
	}

	/**
	 * Set the PC
	 * @param pc
	 * @param pcProperties
	 */
	public void setPc(PlayerCharacter pc, Properties pcProperties) {
		this.pc = pc;
		this.pcProperties = pcProperties;
	}

	/**
	 * Refresh the pane
	 */
	public void refresh() {
		int numDay = VarToken.getIntVarToken(pc, "BardicMusicLevel", false);
		if (numDay > 0) {
			setVisible(true);

			musicText
					.setText("Effects (Perform ranks required): Inspire Courage(3), Countersong(3), Fascinate(3), Inspire Competence(6), Suggestion(9), Inspire Greatness(12), Song of Freedom (15), Inspire Heroics (18)");
			addCheckBoxes(numDay);
		} else {
			setVisible(false);
		}
		updatePane();
	}

	private void addCheckBoxes(int numDay) {
		if (checkList.size() != numDay) {
			checkList.clear();
			for (int i = 0; i < numDay; i++) {
				if (i % 5 == 0 && i != 0) {
					javax.swing.JLabel bufLabel = new javax.swing.JLabel();
					bufLabel.setFont(new java.awt.Font("Dialog", 0, 10));
					bufLabel.setText(" ");
					checkPanel.add(bufLabel);
				}
				JCheckBox checkBox = new JCheckBox();
				checkBox.setBackground(CharacterPanel.white);
				checkBox.setBorder(null);
				checkList.add(checkBox);
				checkPanel.add(checkBox);
				checkBox.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						pc.setDirty(true);
						updateProperties();
					}
				});
			}
		}
	}

	/**
	 * Update the properties
	 */
	public void updateProperties() {
		if (updateProperties) {
			int counter = 0;
			for (int i = 0; i < checkList.size(); i++) {
				JCheckBox checkBox = (JCheckBox) checkList.get(i);
				if (checkBox.isSelected()) {
					counter++;
				}
			}
			pcProperties.put("cs.MusicPane", new Integer(counter).toString());
		}
	}

	/**
	 * Update the music pane
	 */
	public void updatePane() {
		try {
			int counter = Integer.parseInt((String) pcProperties
					.get("cs.MusicPane"));
			for (int i = 0; i < checkList.size(); i++) {
				JCheckBox checkBox = (JCheckBox) checkList.get(i);
				if (counter > 0) {
					checkBox.setSelected(true);
					counter--;
				} else {
					checkBox.setSelected(false);
				}
			}
			updateProperties = true;
		} catch (NumberFormatException e) {
			// Do nothing
		}
	}

	/** Destroy */
	public void destruct() {
		// Put any code here that is needed to prevent memory leaks when this
		// panel is destroyed
	}
}
