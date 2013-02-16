import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

class Ordenamientos extends Frame{
	JTextField TamañoT,TiempoB,TiempoQ,TiempoS;
	JLabel TamañoL,ArL,BuL,QuL,ShL,tiempo;
	JList Ar,Bu,Qu,Sh;
	JScrollPane ArS,BuS,QuS,ShS;
	JButton TamañoB,GraficaB,OrdenarB;
	long ar[];
	int tam;
	
	DataOutputStream max;
	
	//*
	
	Ordenamientos(){
		super("Ordenamientos");
		
		setLayout(null);
		
		TamañoL=new JLabel("No. de Datos:");
		TamañoL.setBounds(10,35,80,20);
		add(TamañoL);
		
		TamañoT=new JTextField();
		TamañoT.setBounds(100,35,100,20);
		add(TamañoT);
		
		TamañoB=new JButton("Generar Datos");
		TamañoB.setBounds(220,35,120,20);
		add(TamañoB);
		
		GraficaB=new JButton("Graficar");
		GraficaB.setBounds(390,35,80,20);
		add(GraficaB);
		
		ArL=new JLabel("Datos:");
		ArL.setBounds(5,60,80,20);
		add(ArL);
		
		Ar=new JList();
		
		ArS=new JScrollPane(Ar);
		ArS.setBounds(5,90,80,250);
		add(ArS);
		
		Icon im=new ImageIcon("kakashi.jpg");
		OrdenarB=new JButton("Ordenar",im);
		OrdenarB.setBounds(105,185,140,60);
		add(OrdenarB);
		
		BuL=new JLabel("Burbuja:");
		BuL.setBounds(265,60,80,20);
		add(BuL);
		
		Bu=new JList();
		
		BuS=new JScrollPane(Bu);
		BuS.setBounds(265,90,80,250);
		add(BuS);
		
		QuL=new JLabel("Quicksort:");
		QuL.setBounds(355,60,80,20);
		add(QuL);
		
		Qu=new JList();
		
		QuS=new JScrollPane(Qu);
		QuS.setBounds(355,90,80,250);
		add(QuS);
		
		ShL=new JLabel("ShellSort");
		ShL.setBounds(445,60,80,20);
		add(ShL);
		
		Sh=new JList();
		
		ShS=new JScrollPane(Sh);
		ShS.setBounds(445,90,80,250);
		add(ShS);
		
		tiempo=new JLabel("Tiempo:");
		tiempo.setBounds(195,350,60,20);
		add(tiempo);
		
		TiempoB=new JTextField();
		TiempoB.setEditable(false);
		TiempoB.setBounds(265,350,80,20);
		add(TiempoB);
		
		TiempoQ=new JTextField();
		TiempoQ.setEditable(false);
		TiempoQ.setBounds(355,350,80,20);
		add(TiempoQ);
		
		TiempoS=new JTextField();
		TiempoS.setEditable(false);
		TiempoS.setBounds(445,350,80,20);
		add(TiempoS);
		
		GenDat generarD=new GenDat();
		TamañoB.addActionListener(generarD);
		
		OrdDat ordenarD=new OrdDat();
		OrdenarB.addActionListener(ordenarD);
		
		GenGraf graficarD=new GenGraf();
		GraficaB.addActionListener(graficarD);
		
		setSize(545,420);
		setVisible(true);
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent evt){
			System.exit(0);}
        });
	}
	
	//*
	
	public class GenDat implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String tamaño=TamañoT.getText();
			tam=Integer.parseInt(tamaño);
			ar=new long[tam];
			for(int inix=0;inix<tam;inix++){
				ar[inix]=(long)(Math.random()*1000);
			}
			
			String aR[]=new String[ar.length];
			Double a;
			String aa;
			for(int in=0;in<ar.length;in++){
				a=(double)(ar[in]);
				aa=a.toString(a);
				aR[in]=aa;
			}
			Ar.setListData(aR);
			JOptionPane.showMessageDialog(null,"Datos Generados con Exito");
		}
	}
	
	//*
	
	public class GenGraf implements ActionListener{
		public void actionPerformed(ActionEvent event){
			BarChartDemo1 demo = new BarChartDemo1("Grafica");
			demo.pack();
			RefineryUtilities.centerFrameOnScreen(demo);
			demo.setVisible(true);
			JOptionPane.showMessageDialog(null,"Grafica Generada con Exito");
			addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent evt){
			System.exit(0);}});
		}
	}
	
	//*
	
	public class OrdDat implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Burbuja bur=new Burbuja();
			Quicksort qui=new Quicksort();
			ShellSort she=new ShellSort();
			JOptionPane.showMessageDialog(null,"Datos Ordenados con Exito");
		}
	}
	
	//*
	
	private class Burbuja{
		public Burbuja(){
			long T3=System.currentTimeMillis();
			long aB[]=new long[ar.length];
			
			for(int in=0;in<ar.length;in++){
				aB[in]=ar[in];
			}
			
			for (int i=1; i<aB.length;i++){
				for(int j=0;j<aB.length-1;j++){
					if (aB[j] > aB[j+1]){
						long aux=aB[j]; 
						aB[j]=aB[j+1];
						aB[j+1]=aux;
					}
				}
			} 
			long TB=(System.currentTimeMillis()-T3)/100;
			Double tb=(double)(TB);
			String TB1=tb.toString((double)(TB));
			TiempoB.setText(TB1);
			System.out.println("TiempoB: "+TB+" Milisegundos"+"\nDatos:");
			
			String ab[]=new String[ar.length];
			Double b;
			String bb;
			for(int in=0;in<ar.length;in++){
				b=(double)(aB[in]);
				bb=b.toString(b);
				ab[in]=bb;
			}
			Bu.setListData(ab);
		}
	}
	
	//*
	
	private class Quicksort{
		public Quicksort(){
			double array[]=new double[ar.length];
			for(int in=0;in<ar.length;in++){
				array[in]=(double)(ar[in]);
			}
			
			Sorter s= new Sorter(array);
			
			long T1=System.currentTimeMillis();
			
			s.sort();
			
			long TQ=((T1)/100000000)/10000;
			
			System.out.println("TiempoQ: "+TQ+" Milisegundos"+"\nDatos:");
			
			long aQ[]=new long[array.length];
			for(int in=0;in<array.length;in++){
				aQ[in]=(long)(array[in]);
			}
			Double tq=(double)(TQ);
			String TQ1=tq.toString((double)(TQ));
			TiempoQ.setText(TQ1);
			
			String aq[]=new String[ar.length];
			Double q;
			String qq;
			for(int in=0;in<ar.length;in++){
				q=(double)(aQ[in]);
				qq=q.toString(q);
				aq[in]=qq;
			}
			Qu.setListData(aq);
		}
	}
	
	//*
	
	private class ShellSort{
		public ShellSort(){
			
			long aS[]=new long[ar.length];
			for(int in=0;in<ar.length;in++){
				aS[in]=ar[in];
			}
			
			int salto=aS.length/2,i,j;
			
			long T2= System.currentTimeMillis();
			
			while (salto>0){
				for (i=salto;i<aS.length;i++){
					j=i-salto;
					while(j>=0){
						i=j+salto;
						if(aS[j]<=aS[i]){
							j=-1;
						}
						else{
							long aux=aS[j];
							aS[j]=aS[i];
							aS[i]=aux;
							j-=salto;
						}
					}
				}
				salto /=2;
			}
			long TS=(System.currentTimeMillis()-T2)/100;
			Double ts=(double)(TS);
			String TS1=ts.toString((double)(TS));
			TiempoS.setText(TS1);
			System.out.println("Tiempo: "+TS+" Milisegundos"+"\nDatos:");
			String as[]=new String[ar.length];
			Double s;
			String ss;
			for(int in=0;in<ar.length;in++){
				s=(double)(aS[in]);
				ss=s.toString(s);
				as[in]=ss;
			}
			Sh.setListData(as);
		}
	}
	
	//*
	
public class BarChartDemo1 extends ApplicationFrame {

    public BarChartDemo1(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(300, 300));
        setContentPane(chartPanel);
    }

    public CategoryDataset createDataset() {
    	String Q1,B1,S1;
    	double q,b,s,t;
    	B1=TiempoB.getText();
    	Q1=TiempoQ.getText();
    	S1=TiempoS.getText();
    	
        b=Double.parseDouble(B1);
        q=Double.parseDouble(Q1);
        s=Double.parseDouble(S1);
        t=b+q+s;
        
        String series1 = "Burbuja";
        String series2 = "Quicksort";
        String series3 = "Shell Sort";
        String series4 = "Tiempo Total";

        String category1 = "Burbuja";
        String category2 = "Quicksort";
        String category3 = "Shell Sort";
        String category4 = "Tiempo Total";
        String category5 = "Category 5";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(b, series1, category1);
        

        dataset.addValue(q, series2, category2);
        

        dataset.addValue(s, series3, category3);
        
        
        dataset.addValue(t, series4, category4);
        
        
        return dataset;
        
    }
    
    public JFreeChart createChart(CategoryDataset dataset) {
        
        JFreeChart chart = ChartFactory.createBarChart(
            "Grafica del Tiempo de los Ordenamientos",       // chart title
            "Ordenamientos",               // domain axis label
            "Tiempo(ms)",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );
        
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 
                0.0f, 0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 
                0.0f, 0.0f, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 
                0.0f, 0.0f, new Color(64, 0, 0));
        GradientPaint gp3 = new GradientPaint(0.0f, 0.0f, Color.orange, 
                0.0f, 0.0f, new Color(0, 0, 64));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        renderer.setSeriesPaint(3, gp3);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 6.0));
        
        return chart;
        
    }
}
	
	//*
	
	public static void main(String args[])throws IOException{
		Ordenamientos ord=new Ordenamientos();
	}
}

//*

class Sorter{

long TQ=System.currentTimeMillis();

  public void sort() {
    quicksort(0,size()-1);
  }

  void quicksort(int i, int j) {
    if (i>=j)
      return;
    int k= partition(i, j);
    quicksort(i, k-1);
    quicksort(k+1, j);
  }

  int partition(int i, int j) {
    int k= i;
    i++;
    for(;;) {
      while (i<j && compare(i,k)<=0)
        i++;
      while (i<j && compare(j,k)>=0)
        j--;
      if (i==j)
        break;
      swap(i,j);
    }
    if (compare(k,i)<0)
      i--;
    swap(k,i);
    return i;
  }

  public double[] array;

  public Sorter(double[] array) {
    this.array= array;
  }

  public void swap(int i, int j) {
    double x= array[i];
    array[i]= array[j];
    array[j]= x;
  }

  public int compare(int i, int j) {
    if (array[i]<array[j]) return -1;
    else if (array[i]==array[j]) return 0;
    else return 1;
  }

  public int size() { return array.length; }


  public void print() {
    for(int i=0; i<size(); i++)
      System.out.print(array[i]+" ");
    System.out.println();
  }
}