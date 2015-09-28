package de.ts.gameengine.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioController
{
	private Properties prop;
	private Sequencer sequencerGame;
	private Sequencer sequencerMenu;
	private InputStream midiFileGameBackGround;
	private InputStream midiFileMenuBackGround;

	public AudioController()
	{
		prop = new Properties();
		try
		{
			prop.load(ClassLoader.getSystemResourceAsStream("sounds.properties"));
			loadMIDIFileForBackgroundMusic();
		}
		catch (IOException e)
		{
			System.out.println("Properties konnte nicht geladen werden");
		}
	}

	public boolean sequencerIsRunning()
	{
		if (sequencerGame == null)
		{
			return false;
		}
		return sequencerGame.isRunning();
	}

	private void loadMIDIFileForBackgroundMusic()
	{
		midiFileGameBackGround = ClassLoader.getSystemResourceAsStream(prop.getProperty("backgroundMusic"));
		midiFileMenuBackGround = ClassLoader.getSystemResourceAsStream(prop.getProperty("backgroundMenuMusic"));
	}

	public void startBackgroundMusic()
	{
		try
		{
			sequencerGame = MidiSystem.getSequencer();
			sequencerGame.open();
			sequencerGame.setSequence(MidiSystem.getSequence(midiFileGameBackGround));
			sequencerGame.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencerGame.start();
		}
		catch (MidiUnavailableException e2)
		{
			System.out.println("MidiUnavailableException");
		}
		catch (InvalidMidiDataException e)
		{
			System.out.println("InvalidMidiDataException");
		}
		catch (IOException e)
		{
			System.out.println("IOException");
		}

	}

	public void startMenuBackgroundMusic()
	{
		try
		{
			sequencerMenu = MidiSystem.getSequencer();
			sequencerMenu.open();
			sequencerMenu.setSequence(MidiSystem.getSequence(midiFileMenuBackGround));
			sequencerMenu.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencerMenu.start();
		}
		catch (MidiUnavailableException e2)
		{
			System.out.println("MidiUnavailableException");
		}
		catch (InvalidMidiDataException e)
		{
			System.out.println("InvalidMidiDataException");
		}
		catch (IOException e)
		{
			System.out.println("IOException");
		}

	}

	public void stopBackgroundMusic()
	{
		if (sequencerGame == null || !sequencerGame.isRunning())
		{
			return;
		}

		sequencerGame.stop();
		sequencerGame.close();
	}

	public void stopMenuBackgroundMusic()
	{
		if (sequencerMenu == null || !sequencerMenu.isRunning())
		{
			return;
		}

		sequencerMenu.stop();
		sequencerMenu.close();
	}

	public synchronized void playSound(final String soundName)
	{
		if (soundName == null || soundName.isEmpty())
		{
			return;
		}

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					playClip(soundName);
				}
				catch (IOException e)
				{
					System.out.println(e.getLocalizedMessage());
				}
				catch (UnsupportedAudioFileException e)
				{
					System.out.println("WRONG AUDIOFILEFORMAT: " + soundName);
				}
				catch (LineUnavailableException e)
				{
					System.out.println("LineUnavailableException");
				}
				catch (InterruptedException e)
				{
					System.out.println("InterruptedException");
				}
			}

			private void playClip(String soundName) throws IOException, UnsupportedAudioFileException,
				LineUnavailableException, InterruptedException
			{
				class AudioListener implements LineListener
				{
					private boolean done = false;

					@Override
					public synchronized void update(LineEvent event)
					{
						Type eventType = event.getType();
						if (eventType == Type.STOP || eventType == Type.CLOSE)
						{
							done = true;
							notifyAll();
						}
					}

					public synchronized void waitUntilDone() throws InterruptedException
					{
						while (!done)
						{
							wait();
						}
					}
				}
				AudioListener listener = new AudioListener();
				final InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(prop
					.getProperty(soundName));
				final BufferedInputStream bufInputStream = new BufferedInputStream(systemResourceAsStream);
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufInputStream);
				try
				{
					Clip clip = AudioSystem.getClip();
					clip.addLineListener(listener);
					clip.open(audioInputStream);
					try
					{
						clip.start();
						listener.waitUntilDone();
					}
					finally
					{
						clip.close();
					}
				}
				finally
				{
					audioInputStream.close();
				}
			}
		}).start();
	}
}
